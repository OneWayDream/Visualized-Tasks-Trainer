<#list content.imports?keys as key>
from ${key} import ${content.imports[key]}
</#list>
<#if content.imports?size != 0>

</#if>
<#if content.isWrapperScrypt>
"""
${content.classAnnotationComment}

ExampleClassWrapper: ExampleClass
class ExampleClassWrapper:
"""

"""
${content.functionAnnotationComment}

example_function_wrapper: example_function
def example_function_wrapper():
"""

"""
${content.visualizationActionRegistrationComment}

args: to_next_step_action_function_name, return_step_action_function_name, time_delay
register_visualization_action('to_next_step_action_function_name', 'return_step_action_function_name', 1000)
"""
</#if>