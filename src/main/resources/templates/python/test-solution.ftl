<#list content.imports?keys as key>
from ${key} import ${content.imports[key]}
</#list>
<#if content.imports?size != 0>

</#if>
"""
${content.comment}
"""
def execute():
    pass

if __name__ == "__main__":
    execute()


