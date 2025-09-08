<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <h2 class="mb-4 text-center"><@spring.message "vacancies.list_title" /></h2>

    <style>
        .btn-group .dropdown-menu {
            min-width: 100%;
        }

        .btn-drop{
            min-width: 200px;
        }

        .card {
            border-radius: 25px;
        }
    </style>

    <div class="d-flex justify-content-end mb-3">
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

    <#if vacancies??>
        <#list vacancies.getContent() as vacancy>
            <div class="card w-100 mb-3 overflow-hidden">
                <div class="card-body">
                    <h5 class="card-title">${vacancy.name}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">
                        <#if vacancy.expFrom?? || vacancy.expTo??>
                            ${vacancy.expFrom!0} - ${vacancy.expTo!0} <@spring.message "vacancies.years_experience" />
                        <#else>
                            <@spring.message "vacancies.no_experience" />
                        </#if>
                    </h6>
                    <p class="card-text">${vacancy.description!}</p>
                    <a href="/vacancies/${vacancy.id}" class="btn btn-outline-info">
                        <@spring.message "vacancies.view_details" />
                    </a>
                    <a href="/vacancies/apply/${vacancy.id}" class="btn btn-outline-success">
                        <@spring.message "vacancies.apply" />
                    </a>
                </div>
            </div>
        </#list>
    </#if>

    <#if vacancies.getTotalElements() gt 0>
        <nav aria-label="..." class="d-flex justify-content-center">
            <ul class="pagination">
                <li class="page-item <#if vacancies.first>disabled</#if>">
                    <a class="page-link" href="?page=${vacancies.getNumber()-1}&size=${vacancies.size}">
                        <@spring.message "pagination.previous" />
                    </a>
                </li>

                <#list 1..vacancies.getTotalPages() as page>
                    <li class="page-item <#if page = vacancies.getNumber() + 1>active</#if>" aria-current="page">
                        <a class="page-link" href="?page=${page - 1}&size=${vacancies.size}">${page}</a>
                    </li>
                </#list>

                <li class="page-item <#if vacancies.last>disabled</#if>">
                    <a class="page-link" href="?page=${vacancies.getNumber()+1}&size=${vacancies.size}">
                        <@spring.message "pagination.next" />
                    </a>
                </li>
            </ul>
        </nav>
    </#if>

</@main.layout>