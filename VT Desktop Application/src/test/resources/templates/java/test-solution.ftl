<#list content.imports as import>
import ${import};
</#list>
<#if content.imports?size != 0>

</#if>
<#if content.comment ??>
/*
    ${content.comment}
*/
</#if>
public class TestSolution extends SolutionScheme {

    @Override
    public void execute() {

    }

}