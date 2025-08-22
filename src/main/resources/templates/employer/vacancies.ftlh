<#import "../layout.ftlh" as main>
<@main.layout>

    <h2 class="mb-4 text-center">Список вакансий</h2>

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
                Sort by
            </button>
            <ul class="dropdown-menu dropdown-menu-end w-100">
                <li><a class="dropdown-item" href="?sort=createdDate,desc">Created date</a></li>
                <li><a class="dropdown-item" href="?sort=responses,desc">Responses</a></li>
            </ul>
        </div>
    </div>




    <#if vacancies??>
        <#list vacancies.getContent() as vacancy>

            <style>
                .card {
                    border-radius: 25px;
                }
            </style>

            <div class="card w-100 mb-3 overflow-hidden">
                <div class="card-body">
                    <h5 class="card-title">${vacancy.name}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">
                        <#if vacancy.expFrom?? || vacancy.expTo??>
                            ${vacancy.expFrom!0} - ${vacancy.expTo!0}
                        <#else>
                            Без опыта
                        </#if>
                    </h6>
                    <p class="card-text">${vacancy.description!}</p>
                    <a href="/vacancies" class="btn btn-outline-info">Card link</a>
                    <a href="/vacancies" class="btn btn-outline-info">Another link</a>
                </div>
            </div>
        </#list>
    </#if>

    <#if vacancies.getTotalElements() gt 0>
        <nav aria-label="..." class="d-flex justify-content-center" >
            <ul class="pagination" >
                <li class="page-item <#if vacancies.first>disabled</#if>">
                    <a class="page-link" href="?page=${vacancies.getNumber()-1}&size=${vacancies.size}">Previous</a>
                </li>

                <#list 1..vacancies.getTotalPages() as page>
                    <li class="page-item <#if page = vacancies.getNumber() + 1>active</#if>" aria-current="page">
                        <a class="page-link" href="?page=${page - 1}&size=${vacancies.size}">${page}</a>
                    </li>
                </#list>

                <li class="page-item <#if vacancies.last>disabled</#if>">
                    <a class="page-link" href="?page=${vacancies.getNumber()+1}&size=${vacancies.size}">Next</a>
                </li>
            </ul>
        </nav>
    </#if>




</@main.layout>