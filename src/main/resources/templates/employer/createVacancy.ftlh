<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <style>
        h2 {
            color: red;
            text-align: center;
            margin-bottom: 30px;
        }
        .form-section {
            margin-bottom: 20px;
        }
        .experience-range input {
            width: 80px;
            display: inline-block;
        }
        .btn-publish {
            background: repeating-linear-gradient(135deg, #d3f4e2, #d3f4e2 10px, #b0e9d1 10px, #b0e9d1 20px);
        }
    </style>

    <h2><@spring.message "vacancy_create.title" /></h2>

    <form action="/vacancies/create" method="post">

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>

        <div class="mb-3">
            <label for="vacancyName" class="form-label"><@spring.message "vacancy_create.name" />:</label>
            <@spring.formInput "vacancyDto.name" 'id="vacancyName" class="form-control"' />
            <@spring.showErrors "br" "error text-danger" />
        </div>

        <div class="mb-3">
            <label for="disabledSelect" class="form-label"><@spring.message "vacancy_create.category" />:</label>
            <select name="categoryId" id="disabledSelect" class="form-select">
                <option><@spring.message "vacancy_create.select_category" /></option>
                <#if categories??>
                    <#list categories as category>
                        <option value="${category.id}">${category.name}</option>
                    </#list>
                </#if>
            </select>
            <@spring.showErrors "<br>" "error text-danger" />
        </div>

        <div class="mb-3">
            <label for="vacancySalary" class="form-label"><@spring.message "vacancy_create.salary" />:</label>
            <@spring.formInput "vacancyDto.salary" 'id="vacancySalary" class="form-control"' />
            <@spring.showErrors "vacancyDto.salary" "error text-danger" />
        </div>

        <div class="mb-3">
            <label for="vacancyDescription" class="form-label"><@spring.message "vacancy_create.description" />:</label>
            <@spring.formTextarea "vacancyDto.description" 'id="vacancyDescription" class="form-control" rows="4"' />
            <@spring.showErrors "vacancyDto.description" "error text-danger" />
        </div>

        <div class="mb-3">
            <label class="form-label"><@spring.message "vacancy_create.experience" />:</label>
            <div class="experience-range d-flex align-items-center gap-2">
                <label for="startDate"><@spring.message "vacancy_create.from" /></label>
                <@spring.formInput "vacancyDto.expFrom" 'id="startDate" class="form-control" placeholder="2"' />
                <@spring.showErrors "<br>" "error text-danger" />
                <label for="endDate"><@spring.message "vacancy_create.to" /></label>
                <@spring.formInput "vacancyDto.expTo" 'id="endDate" class="form-control" placeholder="6"' />
                <@spring.showErrors "<br>" "error text-danger" />
            </div>
        </div>

        <div class="mb-3 form-check">
            <input type="hidden" name="_isActive" value="on">
            <input type="checkbox"
                   name="isActive"
                   class="form-check-input"
                   id="isActiveCheckbox"
                   value="true"
                    ${(vacancyDto.isActive?? && vacancyDto.isActive)?then('checked', '')}>
            <label class="form-check-label" for="isActiveCheckbox">
                <@spring.message "vacancy_create.active" />
            </label>
        </div>

        <button type="submit" class="btn btn-success w-100">
            <@spring.message "vacancy_create.submit" />
        </button>
    </form>
</@main.layout>