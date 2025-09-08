<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <body class="bg-light">

    <div class="text-center mb-4">
        <h2 style="color: red;">
            <@spring.message "profile.title"/>
            <#if user.accountType == "employer">
                <@spring.message "profile.employer" />
            <#elseif user.accountType == "applicant">
                <@spring.message "profile.applicant" />
            </#if>
        </h2>
    </div>

    <div class="d-flex align-items-center justify-content-between mb-4">
        <div class="d-flex align-items-center">

            <#if user.avatar??>
                <img src="/images/byName/${user.avatar}" width="80" height="80" alt="avatar" class="me-3">
            <#else>
                <i class="fas fa-user-circle me-3 rounded" style="font-size: 80px; color: #6c757d;"></i>
            </#if>

            <div>
                <#if user.accountType == "employer">
                    <h5>${user.name!}</h5>
                <#elseif user.accountType == "applicant">
                    <h5>${user.name!}</h5>
                    <p><@spring.message "profile.age" />: ${user.age!}</p>
                </#if>
                <a href="/users/edit" class="btn btn-outline-success btn-sm mt-2">
                    <@spring.message "common.edit" />
                </a>
            </div>
        </div>

        <div>
            <#if user.accountType == "applicant">
                <a href="/resumes/create" class="btn btn-outline-danger">
                    + <@spring.message "profile.create_resume" />
                </a>
            <#elseif user.accountType == "employer">
                <a href="/vacancies/create" class="btn btn-outline-danger">
                    + <@spring.message "profile.create_vacancy" />
                </a>
            </#if>
            <#if user.accountType == "applicant">
                <a href="#" class="btn btn-outline-secondary position-relative ms-3">
                    <@spring.message "profile.responses" />
                    <#--                        <#if responsesCount?? && responsesCount > 0>-->
                    <#--                            <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">-->
                    <#--                            ${responsesCount}-->
                    <#--                        </span>-->
                    <#--                        </#if>-->
                </a>
            </#if>
        </div>
    </div>

    <div>
        <h5>
            <#if user.accountType == "employer">
                <@spring.message "profile.created_vacancies" />
            <#else>
                <@spring.message "profile.resumes" />
            </#if>
        </h5>

        <style>
            .btn-group .dropdown-menu {
                min-width: 100%;
            }

            .btn-drop{
                min-width: 200px;
            }
        </style>

        <div class="d-flex justify-content-end  mb-3">
            <div class="btn-group w-auto">
                <button type="button" class="btn btn-outline-info dropdown-toggle btn-drop" data-bs-toggle="dropdown" aria-expanded="false">
                    <@spring.message "common.sort_by" />
                </button>
                <ul class="dropdown-menu dropdown-menu-end w-100">
                    <li><a class="dropdown-item" href="?sort=createdDate,desc">
                            <@spring.message "sort.created_date" />
                        </a></li>
                    <li><a class="dropdown-item" href="?sort=responses,desc">
                            <@spring.message "sort.responses" />
                        </a></li>
                </ul>
            </div>
        </div>

        <div class="row mb-3">
            <#if items??>
                <#list items.getContent() as item>
                    <div class="col-md-4">
                        <div class="card mb-3 shadow-sm">
                            <div class="card-body">
                                <h6 class="card-title">${item.name}</h6>
                                <p class="card-text text-muted">
                                    <@spring.message "common.updated_date" />: ${item.updateTimeFormatted!""}
                                </p>
                                <#if user.accountType == "applicant">
                                    <div class="d-flex justify-content-between">
                                        <a href="/resumes/update/${item.id}" class="btn btn-outline-primary btn-sm">
                                            <@spring.message "common.update" />
                                        </a>
                                        <a href="/resumes/edit/${item.id}" class="btn btn-outline-success btn-sm">
                                            <@spring.message "common.edit" />
                                        </a>
                                    </div>
                                <#elseif user.accountType == "employer">
                                    <div class="d-flex justify-content-between">
                                        <a href="/vacancies/update/${item.id}" class="btn btn-outline-primary btn-sm">
                                            <@spring.message "common.update" />
                                        </a>
                                        <a href="/vacancies/edit/${item.id}" class="btn btn-outline-success btn-sm">
                                            <@spring.message "common.edit" />
                                        </a>
                                    </div>
                                </#if>

                            </div>
                        </div>
                    </div>
                </#list>
            </#if>
        </div>

        <#if items.getTotalElements() gt 0>
            <nav aria-label="..." class="d-flex justify-content-center" >
                <ul class="pagination" >
                    <li class="page-item <#if items.first>disabled</#if>">
                        <a class="page-link" href="?page=${items.getNumber()-1}&size=${items.size}">
                            <@spring.message "pagination.previous" />
                        </a>
                    </li>

                    <#list 1..items.getTotalPages() as page>
                        <li class="page-item <#if page = items.getNumber() + 1>active</#if>" aria-current="page">
                            <a class="page-link" href="?page=${page - 1}&size=${items.size}">${page}</a>
                        </li>
                    </#list>

                    <li class="page-item <#if items.last>disabled</#if>">
                        <a class="page-link" href="?page=${items.getNumber()+1}&size=${items.size}">
                            <@spring.message "pagination.next" />
                        </a>
                    </li>
                </ul>
            </nav>
        </#if>
    </div>

    </body>

</@main.layout>