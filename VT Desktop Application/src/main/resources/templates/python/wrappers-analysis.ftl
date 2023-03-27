import json
import io
<#list content.modules as module>
import ${module}
</#list>

OUTPUT_FILENAME = "target/wrappers-names.json"

modules = [
<#list 0..content.modules?size-1 as index>
    ${content.modules[index]}<#if index < content.modules?size-1>,</#if>
</#list>
]

wrappers_names_map = {}

for module in modules:
    wrappers_names_map.update(module.__annotations__)

json.dump({'content': wrappers_names_map}, io.open(OUTPUT_FILENAME, 'w+', encoding='utf8'), ensure_ascii=False)