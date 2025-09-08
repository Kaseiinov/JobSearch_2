<#import "spring.ftl" as spring>
<#macro layout>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <title>Title</title>
    </head>
    <body>

    <#assign known = SPRING_SECURITY_CONTEXT?? >
    <#if known>
        <#assign
        user = SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        >
    </#if>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/"><@spring.message "layout.home" /></a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="vacanciesDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <@spring.message "layout.vacancies" />
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="vacanciesDropdown">
                            <li><a class="dropdown-item" href="/vacancies"><@spring.message "layout.vacancies" /></a></li>
                            <li><a class="dropdown-item" href="/vacancies/create"><@spring.message "layout.vacancies_create" /></a></li>
                        </ul>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="resumesDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <@spring.message "layout.resumes" />
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="resumesDropdown">
                            <li><a class="dropdown-item" href="/resumes"><@spring.message "layout.resumes" /></a></li>
                            <li><a class="dropdown-item" href="/resumes/create"><@spring.message "layout.resumes_create" /></a></li>
                        </ul>

                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <@spring.message "layout.dropdown_change_lang" />
                        </a>

                        <ul class="dropdown-menu" aria-labelledby="resumesDropdown">
                            <li><a class="dropdown-item" href="?lang=ru"><@spring.message "layout.dropdown_change_lang_ru" /></a></li>
                            <li><a class="dropdown-item" href="?lang=en"><@spring.message "layout.dropdown_change_lang_en" /></a></li>
                        </ul>
                    </li>

                </ul>

                <#if user??>
                    <a href="/users/profile" class="nav-link mr4-l text-white">
                        <@spring.message "auth.profile" />
                    </a>
                    <#if _csrf??>
                        <form action="/logout" method="post">
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                            <button type="submit" class="btn btn-outline-info m-2" id="logout">
                                <@spring.message "auth.logout" />
                            </button>
                        </form>
                    <#else>
                        <a href="/logout" class="btn btn-outline-info m-2" id="logout">
                            <@spring.message "auth.logout" />
                        </a>
                    </#if>
                <#else>
                    <a href="/auth/login" class="btn btn-outline-info m-2">
                        <@spring.message "auth.login" />
                    </a>
                    <a href="/auth/register" class="btn btn-outline-info m-2">
                        <@spring.message "auth.register" />
                    </a>
                </#if>
            </div>
        </div>
    </nav>

    <div class="container">
        <#nested spring>
    </div>

    </body>
    </html>
</#macro>