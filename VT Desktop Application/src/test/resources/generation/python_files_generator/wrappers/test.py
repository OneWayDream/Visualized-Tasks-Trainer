from lib.app.application_utils import register_visualization_action

"""
If you have a wrapping class, define the wrapped class in the annotation as follows:


ExampleClassWrapper: ExampleClass
class ExampleClassWrapper:
"""

"""
If you have a wrapping function, define the wrapped function in the annotation as follows:


example_function_wrapper: example_function
def example_function_wrapper():
"""

"""
To bind your code and the visualization scene, register the visualization action in 
the code when it is to be executed. Use this method to do it:


args: to_next_step_action_function_name, return_step_action_function_name, time_delay
register_visualization_action('to_next_step_action_function_name', 'return_step_action_function_name', 1000)
"""
