<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <style>
        .card {
            border-radius: 25px;
        }

        .btn-group .dropdown-menu {
            min-width: 100%;
        }

        .btn-drop{
            min-width: 200px;
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

    <h2 class="mb-4 text-center"><@spring.message "resumes.list_title" /></h2>

    <#if resumes??>
        <#list resumes.getContent() as resume>
            <div class="card p-3 mb-4">
                <div class="d-flex align-items-start">
                    <#if resume.user??>
                        <#if resume.user.avatar?? && resume.user.avatar.fileName??>
                            <img src="/images/byName/${resume.user.avatar.fileName}"
                                 width="80"
                                 height="80"
                                 alt="avatar"
                                 class="me-3 rounded">
                        <#else>
                            <i class="fas fa-user-circle me-3 rounded" style="font-size: 80px; color: #6c757d;"></i>
                        </#if>
                    </#if>
                    <div class="flex-grow-1">
                        <h5 class="mb-1">${resume.name}</h5>

                        <#if resume.user?? && resume.user.age??>
                            <div class="text-muted small">
                                ${resume.user.age} <@spring.message "resumes.years" /> •
                                <span class="text-success">
                                    <@spring.message "resumes.updated" /> ${resume.updateTime!}
                                </span>
                            </div>
                        <#else>
                            <div class="text-muted small">
                                <span class="text-success">
                                    <@spring.message "resumes.updated" /> ${resume.updateTime!}
                                </span>
                            </div>
                        </#if>
                    </div>
                </div>

                <hr>

                <div class="small mb-2">
                    <strong><@spring.message "resumes.experience" /></strong><br>
                    <#if resume.workExperience?? && resume.workExperience.years??>
                        ${resume.workExperience.years} <@spring.message "resumes.years" />
                    <#else>
                        <@spring.message "resumes.no_experience" />
                    </#if>
                </div>

                <#if resume.workExperience?? && resume.workExperience.companyName??>
                    <div class="small mb-2">
                        <strong><@spring.message "resumes.last_job" /></strong><br>
                        <span class="fw-bold">${resume.workExperience.companyName}</span><br>
                        <a href="#" class="text-primary small">
                            <@spring.message "resumes.more_details" />
                        </a>
                    </div>
                </#if>
            </div>
        </#list>
    </#if>

    <#if resumes.getTotalElements() gt 0>
        <nav aria-label="..." class="d-flex justify-content-center">
            <ul class="pagination">
                <li class="page-item <#if resumes.first>disabled</#if>">
                    <a class="page-link" href="?page=${resumes.getNumber()-1}&size=${resumes.size}">
                        <@spring.message "pagination.previous" />
                    </a>
                </li>

                <#list 1..resumes.getTotalPages() as page>
                    <li class="page-item <#if page = resumes.getNumber() + 1>active</#if>" aria-current="page">
                        <a class="page-link" href="?page=${page - 1}&size=${resumes.size}">${page}</a>
                    </li>
                </#list>

                <li class="page-item <#if resumes.last>disabled</#if>">
                    <a class="page-link" href="?page=${resumes.getNumber()+1}&size=${resumes.size}">
                        <@spring.message "pagination.next" />
                    </a>
                </li>
            </ul>
        </nav>
    </#if>

</@main.layout>