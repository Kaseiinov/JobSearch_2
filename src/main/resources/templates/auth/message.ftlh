<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="text-center">
        <h2><@spring.message "reset_password_page.title" /></h2>
        <#if message??>
            <p class="text-warning">${message}</p>
        </#if>
    </div>
</@main.layout>