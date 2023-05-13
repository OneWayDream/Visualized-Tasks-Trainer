import json
import io

OUTPUT_FILENAME = "target/visualization-actions.json"

visualization_actions = {}
visualization_actions_counter = 0

def register_visualization_action(to_next_step_action_function_name, return_step_action_function_name, delay):
    global visualization_actions_counter
    visualization_actions[visualization_actions_counter] = {
        'nextStepCommand': to_next_step_action_function_name,
        'previousStepCommand': return_step_action_function_name,
        'stepDelay': delay
    }
    visualization_actions_counter += 1

def write_visualization_actions():
    json.dump({'actionMap': visualization_actions}, io.open(OUTPUT_FILENAME, 'w+', encoding='utf8'), ensure_ascii=False)
