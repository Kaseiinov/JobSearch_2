<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <form action="/resumes/create" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>

        <div class="mb-3">
            <label for="form3Example3cg" class="form-label"><@spring.message "resume_create.name" /></label>
            <@spring.formInput "resumeDto.name" 'id="form3Example3cg" class="form-control"' />
            <@spring.showErrors "<br>" "error text-danger" />
        </div>

        <div class="mb-3">
            <label for="disabledSelect" class="form-label"><@spring.message "resume_create.category" /></label>
            <select name="categoryId" id="disabledSelect" class="form-select">
                <option><@spring.message "resume_create.select_category" /></option>
                <#if categories??>
                    <#list categories as category>
                        <option value="${category.id}">${category.name}</option>
                    </#list>
                </#if>
            </select>
            <@spring.showErrors "<br>" "error text-danger" />
        </div>

        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label"><@spring.message "resume_create.salary" /></label>
            <@spring.formInput "resumeDto.salary" 'id="exampleInputPassword1" class="form-control"' />
            <@spring.showErrors "<br>" "error text-danger" />
        </div>

        <div class="card mb-3">
            <div class="card-body">
                <p class="mb-3"><@spring.message "resume_create.about" /></p>

                <div class="mb-2">
                    <label for="workYear" class="form-label"><@spring.message "resume_create.work_years" />:</label>
                    <@spring.formInput "resumeDto.workExperience[0].years" 'id="workYear" class="form-control"' />
                    <@spring.showErrors "<br>" "error text-danger" />
                </div>

                <div class="mb-2">
                    <label for="companyName" class="form-label"><@spring.message "resume_create.company" />:</label>
                    <@spring.formInput "resumeDto.workExperience[0].companyName" 'id="companyName" class="form-control"' />
                    <@spring.showErrors "<br>" "error text-danger" />
                </div>

                <div class="mb-2">
                    <label for="position" class="form-label"><@spring.message "resume_create.position" />:</label>
                    <@spring.formInput "resumeDto.workExperience[0].position" 'id="position" class="form-control"' />
                    <@spring.showErrors "<br>" "error text-danger" />
                </div>

                <div class="mb-2">
                    <label for="responsibilities" class="form-label"><@spring.message "resume_create.responsibilities" />:</label>
                    <@spring.formInput "resumeDto.workExperience[0].responsibilities" 'id="responsibilities" class="form-control"' />
                    <@spring.showErrors "<br>" "error text-danger" />
                </div>
            </div>
        </div>

        <div class="card">
            <div class="card-body">
                <div class="mb-2">
                    <label for="degree" class="form-label"><@spring.message "resume_create.education" />:</label>
                    <@spring.formInput "resumeDto.educations[0].degree" 'id="degree" class="form-control"' />
                    <@spring.showErrors "<br>" "error text-danger" />
                </div>

                <div class="mb-2">
                    <label for="university" class="form-label"><@spring.message "resume_create.institution" />:</label>
                    <@spring.formInput "resumeDto.educations[0].institution" 'id="university" class="form-control"' />
                    <@spring.showErrors "<br>" "error text-danger" />
                </div>

                <div class="mb-2">
                    <label for="startDate" class="form-label"><@spring.message "resume_create.start_date" />:</label>
                    <@spring.formInput "resumeDto.educations[0].startDate" 'id="startDate" class="form-control" placeholder="2020-05-25"' />
                    <@spring.showErrors "<br>" "error text-danger" />
                </div>

                <div class="mb-2">
                    <label for="endDate" class="form-label"><@spring.message "resume_create.end_date" />:</label>
                    <@spring.formInput "resumeDto.educations[0].endDate" 'id="endDate" class="form-control" placeholder="2020-05-25"' />
                    <@spring.showErrors "<br>" "error text-danger" />
                </div>
            </div>
        </div>

        <div class="mb-3">
            <label class="form-label"><@spring.message "resume_create.social_links" />:</label>
            <div class="d-flex align-items-center mb-2">
                <img src="https://img.icons8.com/ios-glyphs/30/new-post.png" class="me-2" alt="email">
                <input type="hidden" name="contacts[0].typeId" value="1">
                <input type="email" name="contacts[0].value" class="form-control" placeholder="<@spring.message 'resume_create.email_placeholder' />">
            </div>
            <div class="d-flex align-items-center mb-2">
                <img src="https://img.icons8.com/ios-glyphs/30/phone.png" class="me-2" alt="phone">
                <input type="hidden" name="contacts[1].typeId" value="2">
                <input type="tel" name="contacts[1].value" class="form-control" placeholder="<@spring.message 'resume_create.phone_placeholder' />">
            </div>
            <div class="d-flex align-items-center mb-2">
                <img src="https://img.icons8.com/ios-filled/30/facebook.png" class="me-2" alt="fb">
                <input type="hidden" name="contacts[2].typeId" value="4">
                <input type="text" name="contacts[2].value" class="form-control" placeholder="Facebook">
            </div>
            <div class="d-flex align-items-center mb-2">
                <img src="https://img.icons8.com/ios-filled/30/linkedin.png" class="me-2" alt="linkedin">
                <input type="hidden" name="contacts[3].typeId" value="5">
                <input type="text" name="contacts[3].value" class="form-control" placeholder="LinkedIn">
            </div>
            <div class="d-flex align-items-center mb-2">
                <img src="https://img.icons8.com/ios-filled/30/telegram-app.png" class="me-2" alt="telegram">
                <input type="hidden" name="contacts[4].typeId" value="3">
                <input type="text" name="contacts[4].value" class="form-control" placeholder="Telegram">
            </div>
        </div>

        <div class="mb-3 form-check">
            <input type="hidden" name="_isActive" value="on">
            <input type="checkbox"
                   name="isActive"
                   class="form-check-input"
                   id="isActiveCheckbox"
                   value="true"
                    ${(resumeDto.isActive?? && resumeDto.isActive)?then('checked', '')}>
            <label class="form-check-label" for="isActiveCheckbox">
                <@spring.message "resume_create.active" />
            </label>
        </div>

        <button type="submit" class="btn btn-primary">
            <@spring.message "resume_create.submit" />
        </button>
    </form>

</@main.layout>