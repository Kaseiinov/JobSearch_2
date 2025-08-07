<#import "layout.ftlh" as main>
<@main.layout>

    <style>
        .card {
            border-radius: 25px;
        }
    </style>

    <h2 class="mb-4 text-center">Список вакансий</h2>

    <#if vacancies??>
        <#list vacancies as vacancy>

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
                    <a href="/" class="btn btn-outline-info">Card link</a>
                    <a href="/" class="btn btn-outline-info">Another link</a>
                </div>
            </div>
        </#list>
    </#if>

</@main.layout>