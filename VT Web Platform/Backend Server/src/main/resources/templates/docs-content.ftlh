<#import "spring.ftl" as spring/>

<#macro mainConcepts>
    <h2 class="article-header"><@spring.message 'main-concepts.about-functionality.title'/></h2>
    <p><@spring.message 'main-concepts.about-functionality.text.p-1'/></p>
    <p><@spring.message 'main-concepts.about-functionality.text.p-2'/></p>
    <p><@spring.message 'main-concepts.about-functionality.text.p-3'/></p>
    <div class="line"></div>
    <h2 class="article-header"><@spring.message 'main-concepts.about-application.title'/></h2>
    <p><@spring.message 'main-concepts.about-functionality.text.p-1-1'/><a class="link" href="https://github.com/OneWayDream/Visualized-Tasks-Trainer"><@spring.message 'main-concepts.about-application.link-text-1'/></a><@spring.message 'main-concepts.about-application.text.p-1-2'/></p>
    <p><@spring.message 'main-concepts.about-application.text.p-2'/></p>
</#macro>

<#macro developing_visualization>
    <h2 class="article-header"><@spring.message 'developing.visualization.supported-visualizations.title'/></h2>
    <p><@spring.message 'developing.visualization.supported-visualizations.text.p-1'/></p>
    <ul>
        <li><@spring.message 'developing.visualization.supported-visualizations.types-list.1'/></li>
        <li><@spring.message 'developing.visualization.supported-visualizations.types-list.2'/></li>
    </ul>
    <div class="line"></div>
    <h2 class="article-header"><@spring.message 'developing.visualization.how-to-use.title'/></h2>
    <p><@spring.message 'developing.visualization.how-to-use.text.p-1'/></p>
    <ol>
        <li>
            <p><span class="bold"><@spring.message 'developing.visualization.how-to-use.files-list.1.name'/></span> - <@spring.message 'developing.visualization.how-to-use.files-list.1.description'/></p>
        </li>
        <li>
            <p><span class="bold"><@spring.message 'developing.visualization.how-to-use.files-list.2.name'/></span> - <@spring.message 'developing.visualization.how-to-use.files-list.2.description'/></p>
        </li>
    </ol>
    <p><@spring.message 'developing.visualization.how-to-use.text.p-2-1'/><span class="bold"><@spring.message 'developing.visualization.how-to-use.text.p-2.arg-1'/></span><@spring.message 'developing.visualization.how-to-use.text.p-2-2'/></p>
    <div class="line"></div>
    <h2 class="article-header"><@spring.message 'developing.visualization.visualization-scene-methods.title'/></h2>
    <p><@spring.message 'developing.visualization.visualization-scene-methods.text.p-2'/></p>
    <h3><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.1.name'/></h3>
    <p><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.1.description'/> <span class="bold"><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.1.vars.1'/></span>, <span class="bold"><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.1.vars.2'/></span> and <span class="bold"><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.1.vars.3'/></span>.</p>
    <table>
        <tr>
            <th><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.1.vars-table.header-1'/></th>
            <th><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.1.vars-table.header-2'/></th>
        </tr>
        <tr>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.1.vars-table.1-1'/></td>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.1.vars-table.1-2'/></td>
        </tr>
        <tr>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.1.vars-table.2-1'/></td>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.1.vars-table.2-2'/></td>
        </tr>
        <tr>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.1.vars-table.3-1'/></td>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.1.vars-table.3-2'/></td>
        </tr>
    </table>
    <pre class="code-block">
        <code>
    @Override
    protected void adjustControlButtons() {
        isStartEndButtonsEnabled = true;
        isForwardBackStepButtonsEnabled = true;
        isPlay
    }
        </code>
    </pre>
    <div class="line"></div>
    <h2 class="article-header"><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.2.name'/></h2>
    <p><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.2.description'/></p>
    <table>
        <tr>
            <th><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.2.vars-table.header-1'/></th>
            <th><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.2.vars-table.header-2'/></th>
        </tr>
        <tr>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.2.vars-table.1-1'/></td>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.2.vars-table.1-2'/></td>
        </tr>
        <tr>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.2.vars-table.2-1'/></td>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.2.vars-table.2-2'/></td>
        </tr>
        <tr>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.2.vars-table.3-1'/></td>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.2.vars-table.3-2'/></td>
        </tr>
        <tr>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.2.vars-table.4-1'/></td>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.2.vars-table.4-2'/></td>
        </tr>
        <tr>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.2.vars-table.5-1'/></td>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.2.vars-table.5-2'/></td>
        </tr>
        <tr>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.2.vars-table.6-1'/></td>
            <td><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.2.vars-table.6-2'/></td>
        </tr>
    </table>
    <pre class="code-block">
        <code>
    @Override
    protected void adjustEdgesCommands() {
        inInitialStateCommand = "initialStyle";
        initialStepDelay = 1000;
        atSceneStartCommand = "initialStyle";
        atStartStepDelay = 1000;
        atSceneEndCommand = "thirdAnimation";
        atEndStepDelay = 1000;
    }
        </code>
    </pre>
    <div class="line"></div>
    <h3 class="article-header"><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.3.name'/></h3>
    <p><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.3.description'/></p>
    <div class="line"></div>
    <h3 class="article-header"><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.4.name'/></h3>
    <p><@spring.message 'developing.visualization.visualization-scene-methods.methods-list.4.description'/></p>
    <div class="line"></div>
    <h2 class="article-header"><@spring.message 'developing.visualization.adding-visual-step.title'/></h2>
    <p><@spring.message 'developing.visualization.adding-visual-step.text.p-1'/></p>
    <p><@spring.message 'developing.visualization.adding-visual-step.text.p-2'/></p>
    <table>
        <tr>
            <th><@spring.message 'developing.visualization.adding-visual-step.args-list.header-1'/></th>
            <th><@spring.message 'developing.visualization.adding-visual-step.args-list.header-2'/></th>
        </tr>
        <tr>
            <td><@spring.message 'developing.visualization.adding-visual-step.args-list.1-1'/></td>
            <td><@spring.message 'developing.visualization.adding-visual-step.args-list.1-2'/></td>
        </tr>
        <tr>
            <td><@spring.message 'developing.visualization.adding-visual-step.args-list.2-1'/></td>
            <td><@spring.message 'developing.visualization.adding-visual-step.args-list.2-2'/></td>
        </tr>
        <tr>
            <td><@spring.message 'developing.visualization.adding-visual-step.args-list.3-1'/></td>
            <td><@spring.message 'developing.visualization.adding-visual-step.args-list.3-2'/></td>
        </tr>
    </table>
    <div class="code-block">
        <div class="language-buttons">
            <img id="registrationJavaLanguageButton" class="language-button btn" src="<@spring.url '/images/icons/java.png'/>" alt="" width="40px" height="40px"/>
            <img id="registrationPythonLanguageButton" class="language-button btn" src="<@spring.url '/images/icons/python.png'/>" alt="" width="40px" height="40px"/>
        </div>
        <pre id="registrationJavaCode">
            <code>
    JavaVisualizationActionRegistrationManager.registerAction("firstAnimation", "initialStyle", 1000);
            </code>
        </pre>
        <pre id="registrationPythonCode">
            <code>
    register_visualization_action('firstAnimation', 'initialStyle', 1000)
            </code>
        </pre>
    </div>
</#macro>

<#macro developing_taskUpload>
    <h2 class="article-header"><@spring.message 'developing.task-upload.title'/></h2>
    <ol>
        <li>
            <p><@spring.message 'education.task-upload.text.steps.1'/></p>
        </li>
        <li>
            <p><@spring.message 'education.task-upload.text.steps.2'/></p>
        </li>
        <li>
            <p><@spring.message 'education.task-upload.text.steps.3'/></p>
            <ul>
                <li>
                    <p><@spring.message 'education.task-upload.text.steps.3.notes.1'/></p>
                </li>
                <li>
                    <p><@spring.message 'education.task-upload.text.steps.3.notes.2'/></p>
                </li>
            </ul>
        </li>
    </ol>
</#macro>

<#macro developing_wrapping>
    <h2 class="article-header"><@spring.message 'developing.wrapping.title'/></h2>
    <p><@spring.message 'developing.wrapping.text.p1'/></p>
    <p><@spring.message 'developing.wrapping.text.p2-1'/><a class="link" href="<@spring.url '/docs/developing/visualization'/>"><@spring.message 'developing.wrapping.text.p2.arg-1'/></a><@spring.message 'developing.wrapping.text.p2-2'/></p>
    <p><@spring.message 'developing.wrapping.text.p3'/></p>
    <div class="code-block">
        <div class="language-buttons">
            <img id="wrappingJavaLanguageButton" class="language-button btn" src="<@spring.url '/images/icons/java.png'/>" alt="" width="40px" height="40px"/>
            <img id="wrappingPythonLanguageButton" class="language-button btn" src="<@spring.url '/images/icons/python.png'/>" alt="" width="40px" height="40px"/>
        </div>
        <pre id="wrappingJavaCode">
            <code>
    // <@spring.message 'developing.wrapping.wrapping-code-comment.for-class'/>
    @WrappedClass(className = "WrappedClass")
    public class Wrapper {
            </code>
        </pre>
        <pre id="wrappingPythonCode">
            <code>
    # <@spring.message 'developing.wrapping.wrapping-code-comment.for-class'/>
    ExampleClassWrapper: ExampleClass
    class ExampleClassWrapper():

    # <@spring.message 'developing.wrapping.wrapping-code-comment.for-function'/>
    example_function_wrapper: example_function
    def example_function_wrapper():
            </code>
        </pre>
    </div>
</#macro>

<#macro education_taskDownload>
    <h2 class="article-header"><@spring.message 'education.task-download.title'/></h2>
    <ol>
        <li>
            <p><@spring.message 'education.task-download.text.steps.1-1'/><a class="link" href="<@spring.url '/tasks'/>"><@spring.message 'education.task-download.text.steps.1.search-page-arg'/></a><@spring.message 'education.task-download.text.steps.1-2'/></p>
        </li>
        <li>
            <p><@spring.message 'education.task-download.text.steps.2'/></p>
        </li>
        <li>
            <p><@spring.message 'education.task-download.text.steps.3'/></p>
        </li>
    </ol>
</#macro>

<#macro education_solution>
    <h2 class="article-header"><@spring.message 'education.solution.solve-instruction.title'/></h2>
    <p><@spring.message 'education.solution.solve-instruction.text.p1'/></p>
    <ol>
        <li>
            <p><@spring.message 'education.solution.solve-instruction.text.steps.1-1'/><a class="link" href="<@spring.url '/docs/education/task-download'/>"><@spring.message 'education.solution.solve-instruction.text.steps.1.instruction-arg'/></a><@spring.message 'education.solution.solve-instruction.text.steps.1-2'/></p>
        </li>
        <li>
            <p><@spring.message 'education.solution.solve-instruction.text.steps.2'/></p>
        </li>
        <li>
            <p><@spring.message 'education.solution.solve-instruction.text.steps.3'/></p>
        </li>
        <li>
            <p><@spring.message 'education.solution.solve-instruction.text.steps.4'/></p>
        </li>
        <li>
            <p><@spring.message 'education.solution.solve-instruction.text.steps.5'/></p>
        </li>
    </ol>
</#macro>