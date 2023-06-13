<#if content.packageValue ??>
package ${content.packageValue};

</#if>
<#if content.imports ??>
<#list content.imports as import>
import ${import};
</#list>
<#if content.imports?size != 0>

</#if>
</#if>
<#if content.isWrapperClass>
/*
    ${content.annotationComment}
*/
/*
    ${content.visualizationActionRegistrationComment}
    VisualizationActionsManager.addAction(new VisualizationAction(
            "toNextStepActionFunctionName", "returnStepActionFunctionName", 1000 (time delay in ms)
    ));
*/
@WrappedClass(classname = "*your wrapped class name*")
</#if>
public class ${content.fileName} {



}