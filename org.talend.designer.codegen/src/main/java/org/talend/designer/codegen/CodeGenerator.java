// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================
package org.talend.designer.codegen;

import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.codegen.jet.JETException;
import org.talend.core.CorePlugin;
import org.talend.core.context.Context;
import org.talend.core.context.RepositoryContext;
import org.talend.core.model.components.IComponentsFactory;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IContext;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.temp.ECodeLanguage;
import org.talend.core.model.temp.ECodePart;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.designer.codegen.config.EInternalTemplate;
import org.talend.designer.codegen.config.JetBean;
import org.talend.designer.codegen.config.NodesSubTree;
import org.talend.designer.codegen.config.NodesTree;
import org.talend.designer.codegen.config.TemplateUtil;
import org.talend.designer.codegen.exception.CodeGeneratorException;
import org.talend.designer.codegen.model.CodeGeneratorEmittersPoolFactory;
import org.talend.designer.codegen.proxy.JetProxy;

/**
 * CodeGenerator.
 * 
 * $Id$
 * 
 */
public class CodeGenerator implements ICodeGenerator {

    private static Logger log = Logger.getLogger(CodeGenerator.class);

    private IProcess process;

    private boolean statistics;

    private boolean trace;

    private String interpreterPath;

    private String libPath;

    private String runtimeFilePath;

    private String currentProjectName;

    private String jobName;

    private String contextName;

    private ECodeLanguage language;

    private List<? extends INode> nodes;

    private NodesTree processTree;

    private static final long INIT_TIMEOUT = 15000; // 15s

    private static final long INIT_PAUSE = 1000; // 1s

    /**
     * Constructor : use the process and laguage to initialize internal components.
     * 
     * @param process
     * @param language
     */
    @SuppressWarnings("unchecked")
    public CodeGenerator(IProcess process, boolean statistics, boolean trace, String... options) {
        if (process == null) {
            throw new NullPointerException();
        } else {
            this.process = process;
            this.statistics = statistics;
            this.trace = trace;
            this.jobName = process.getLabel();
            this.contextName = process.getContextManager().getDefaultContext().getName();

            if ((options != null) && (options.length == 4)) {
                this.interpreterPath = options[0];
                this.libPath = options[1];
                this.runtimeFilePath = options[2];
                this.currentProjectName = options[3];
            } else {
                this.interpreterPath = "";
                this.libPath = "";
                this.runtimeFilePath = "";
                this.currentProjectName = "";
            }

            nodes = (List<? extends INode>) process.getGeneratingNodes();
            processTree = new NodesTree(nodes);
            RepositoryContext repositoryContext = (RepositoryContext) CorePlugin.getContext().getProperty(
                    Context.REPOSITORY_CONTEXT_KEY);
            language = repositoryContext.getProject().getLanguage();
        }
    }

    public CodeGenerator() {
        RepositoryContext repositoryContext = (RepositoryContext) CorePlugin.getContext().getProperty(
                Context.REPOSITORY_CONTEXT_KEY);
        language = repositoryContext.getProject().getLanguage();
    }

    /**
     * Generate the code for the process given to the constructor.
     * 
     * @return the generated code
     * @throws CodeGeneratorException if an error occurs during Code Generation
     */
    @SuppressWarnings("unchecked")
    public String generateProcessCode() throws CodeGeneratorException {
        // Parse Process, generate Code for Individual Components
        // generate Assembly Code for individual Components
        StringBuffer componentsCode = new StringBuffer();

        long startTimer = Calendar.getInstance().getTimeInMillis();
        long endTimer = startTimer;
        try {
            while ((!CodeGeneratorEmittersPoolFactory.isInitialized()) && ((endTimer - startTimer) < INIT_TIMEOUT)) {
                Thread.sleep(INIT_PAUSE);
                endTimer = Calendar.getInstance().getTimeInMillis();
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            throw new CodeGeneratorException(e);
        }
        if ((endTimer - startTimer) > INIT_TIMEOUT) {
            throw new CodeGeneratorException("JET initialisation Time Out");
        } else {
            if ((processTree.getSubTrees() != null) && (processTree.getSubTrees().size() > 0)) {

                Vector headerArgument = new Vector(2);
                headerArgument.add(process);
                headerArgument.add((String) CodeGeneratorActivator.getDefault().getBundle().getHeaders().get(
                        org.osgi.framework.Constants.BUNDLE_VERSION));
                componentsCode.append(generateTypedComponentCode(EInternalTemplate.HEADER, headerArgument));
                for (NodesSubTree subTree : processTree.getSubTrees()) {
                    componentsCode.append(generateTypedComponentCode(EInternalTemplate.SUBPROCESS_HEADER, subTree));
                    componentsCode.append(generateComponentsCode(subTree, subTree.getRootNode(), ECodePart.BEGIN));
                    componentsCode.append(generateComponentsCode(subTree, subTree.getRootNode(), ECodePart.MAIN));
                    componentsCode.append(generateTypedComponentCode(EInternalTemplate.PART_ENDMAIN, null));
                    componentsCode.append(generateComponentsCode(subTree, subTree.getRootNode(), ECodePart.END));
                    componentsCode.append(generateTypedComponentCode(EInternalTemplate.SUBPROCESS_FOOTER, subTree));
                }
                Vector footerArgument = new Vector(2);
                footerArgument.add(process);
                footerArgument.add(processTree.getRootNodes());
                componentsCode.append(generateTypedComponentCode(EInternalTemplate.FOOTER, footerArgument));
                componentsCode
                        .append(generateTypedComponentCode(EInternalTemplate.PROCESSINFO, componentsCode.length()));
            }

            return componentsCode.toString();
        }
    }

    /**
     * Parse Process, and generate Code for Context Variables.
     * 
     * @param designerContext the context to generate code from
     * @return the generated code
     * @throws CodeGeneratorException if an error occurs during Code Generation
     */
    public String generateContextCode(IContext designerContext) throws CodeGeneratorException {
        if (process != null) {
            if (designerContext == null) {
                designerContext = process.getContextManager().getDefaultContext();
            }
            List<IContextParameter> listParameters = designerContext.getContextParameterList();
            if (listParameters != null) {
                JetBean jetBean = initializeJetBean(listParameters);

                jetBean.setTemplateRelativeUri(TemplateUtil.RESOURCES_DIRECTORY + TemplateUtil.DIR_SEP
                        + EInternalTemplate.CONTEXT + TemplateUtil.EXT_SEP + language.getExtension()
                        + TemplateUtil.TEMPLATE_EXT);

                JetProxy proxy = new JetProxy(jetBean);
                String content;
                try {
                    content = proxy.generate();
                } catch (JETException e) {
                    log.error(e.getMessage(), e);
                    throw new CodeGeneratorException(e);
                } catch (CoreException e) {
                    log.error(e.getMessage(), e);
                    throw new CodeGeneratorException(e);
                }
                return content;
            }
        }
        return "";
    }

    /**
     * Generate Code for a given Component.
     * 
     * @param type the internal component template
     * @param argument the bean
     * @return the generated code
     * @throws CodeGeneratorException if an error occurs during Code Generation
     */
    private StringBuffer generateTypedComponentCode(EInternalTemplate type, Object argument)
            throws CodeGeneratorException {
        return generateTypedComponentCode(type, argument, null);
    }

    /**
     * Generate Code Part for a given Component.
     * 
     * @param type the internal component template
     * @param argument the bean
     * @param part part of code to generate
     * @return the genrated code
     * @throws CodeGeneratorException if an error occurs during Code Generation
     */
    private StringBuffer generateTypedComponentCode(EInternalTemplate type, Object argument, ECodePart part)
            throws CodeGeneratorException {
        CodeGeneratorArgument codeGenArgument = new CodeGeneratorArgument();
        codeGenArgument.setNode(argument);
        codeGenArgument.setCodePart(part);
        codeGenArgument.setStatistics(statistics);
        codeGenArgument.setTrace(trace);
        codeGenArgument.setInterpreterPath(interpreterPath);
        codeGenArgument.setLibPath(libPath);
        codeGenArgument.setRuntimeFilePath(runtimeFilePath);
        codeGenArgument.setCurrentProjectName(currentProjectName);
        codeGenArgument.setContextName(contextName);
        codeGenArgument.setJobName(jobName);
        JetBean jetBean = initializeJetBean(codeGenArgument);

        jetBean.setTemplateRelativeUri(TemplateUtil.RESOURCES_DIRECTORY + TemplateUtil.DIR_SEP + type
                + TemplateUtil.EXT_SEP + language.getExtension() + TemplateUtil.TEMPLATE_EXT);

        JetProxy proxy = new JetProxy(jetBean);
        StringBuffer content = new StringBuffer();
        try {
            content.append(proxy.generate());
        } catch (JETException e) {
            log.error(e.getMessage(), e);
            throw new CodeGeneratorException(e);
        } catch (CoreException e) {
            log.error(e.getMessage(), e);
            throw new CodeGeneratorException(e);
        }
        return content;
    }

    
    /**
     * Generate Code Parts for a Sub Process .
     * 
     * @param subProcess the suprocess
     * @param node the subprocess root
     * @param part the part of code to generate
     * @return the generated code
     * @throws CodeGeneratorException if an error occurs during Code Generation
     */
    private StringBuffer generateComponentsCode(NodesSubTree subProcess, INode node, ECodePart part)
            throws CodeGeneratorException {
        StringBuffer codeComponent = new StringBuffer();
        Boolean isMarked = subProcess.isMarkedNode(node, part);
        boolean isIterate = isIterateNode(node);
        if ((isMarked != null) && (!isMarked)) {
            switch (part) {
            case BEGIN:
                if (isIterate) {
                    codeComponent.append(generateComponentCode(node, ECodePart.BEGIN));
                }
                codeComponent = codeComponent.append(generatesTreeCode(subProcess, node, part));
                if ((node.isMultipleMethods()) && (!isIterate)) {
                    codeComponent = codeComponent.append(generateComponentCode(node, ECodePart.BEGIN));
                }
                break;
            case MAIN:
                codeComponent = codeComponent.append(generateComponentCode(node, ECodePart.MAIN));
                codeComponent = codeComponent.append(generatesTreeCode(subProcess, node, part));
                break;
            case END:
                if ((node.isMultipleMethods()) && (!isIterate)) {
                    codeComponent = codeComponent.append(generateComponentCode(node, ECodePart.END));
                }
                codeComponent = codeComponent.append(generatesTreeCode(subProcess, node, part));
                if (isIterate) {
                    codeComponent = codeComponent.append(generateComponentCode(node, ECodePart.END));
                }
                break;
            default:
                // do nothing
            }
            subProcess.markNode(node, part);
        }

        return codeComponent;
    }

    /**
     * Return Type of Node to correctly sort the encapsulated code.
     * 
     * @param node the node to check
     * @return true if the node is an iterate node
     */
    private boolean isIterateNode(INode node) {
        if (node != null) {
            List<? extends IConnection> outGoingConnections = node.getOutgoingConnections();
            if ((outGoingConnections != null) && (outGoingConnections.size() > 0)) {
                IConnection connection = outGoingConnections.get(0);
                if (connection.getLineStyle() == EConnectionType.ITERATE) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Generate this tree Code.
     * 
     * @param subProcess the tree
     * @param node the tree root
     * @param part the part of code to generate
     * @return the generated code
     * @throws CodeGeneratorException if an error occurs during Code Generation
     */
    private StringBuffer generatesTreeCode(NodesSubTree subProcess, INode node, ECodePart part)
            throws CodeGeneratorException {
        StringBuffer code = new StringBuffer();
        for (IConnection connection : node.getOutgoingConnections()) {
            INode targetNode = (INode) connection.getTarget();
            if ((targetNode != null) && (subProcess != null)) {
                code = code.append(generateComponentsCode(subProcess, targetNode, part));
            }
        }
        return code;
    }
    
    /**
     * Generate Part Code for a given Component.
     * 
     * @param node the component
     * @param part the component's part
     * @return the generated code
     * @throws CodeGeneratorException if an error occurs during Code Generation
     */
    public String generateComponentCode(INode node, ECodePart part) throws CodeGeneratorException {     
        CodeGeneratorArgument argument = new CodeGeneratorArgument();
        argument.setNode(node);
        argument.setCodePart(part);
        argument.setStatistics(statistics);
        argument.setTrace(trace);
        argument.setInterpreterPath(interpreterPath);
        argument.setLibPath(libPath);
        argument.setRuntimeFilePath(runtimeFilePath);
        argument.setCurrentProjectName(currentProjectName);
        argument.setContextName(contextName);
        argument.setJobName(jobName);

        JetBean jetBean = initializeJetBean(argument);

        StringBuffer content = new StringBuffer();
        try {
            content = content.append(generateTypedComponentCode(EInternalTemplate.PART_HEADER, node, part));
            jetBean.setTemplateRelativeUri(IComponentsFactory.COMPONENTS_DIRECTORY + TemplateUtil.DIR_SEP
                    + node.getComponent().getName() + TemplateUtil.DIR_SEP + node.getComponent().getName() + "_" + part
                    + TemplateUtil.EXT_SEP + language.getExtension() + TemplateUtil.TEMPLATE_EXT);
            JetProxy proxy = new JetProxy(jetBean);
            content = content.append(proxy.generate());
            content = content.append(generateTypedComponentCode(EInternalTemplate.PART_FOOTER, node, part));
        } catch (JETException jetException) {
            log.error(jetException.getMessage(), jetException);
            throw new CodeGeneratorException(jetException);
        } catch (CoreException coreException) {
            log.error(coreException.getMessage(), coreException);
            throw new CodeGeneratorException(coreException);
        }
        return content.toString();
    }

    /**
     * Initialize Jet Bean to pass to the Jet Generator.
     * 
     * @param argument the node to convert
     * @return the initialized JetBean
     */
    private JetBean initializeJetBean(Object argument) {
        JetBean jetBean = new JetBean();

        if (argument == null) {
            jetBean.setJetPluginRepository(CodeGeneratorActivator.PLUGIN_ID);
        } else {
            if (argument instanceof CodeGeneratorArgument) {
                CodeGeneratorArgument codeArgument = (CodeGeneratorArgument) argument;
                if (codeArgument.getArgument() instanceof INode) {
                    jetBean.setJetPluginRepository(IComponentsFactory.COMPONENTS_LOCATION);
                } else {
                    jetBean.setJetPluginRepository(CodeGeneratorActivator.PLUGIN_ID);
                }
            } else {
                jetBean.setJetPluginRepository(CodeGeneratorActivator.PLUGIN_ID);
            }
        }

        jetBean.setArgument(argument);
        return jetBean;
    }
}
