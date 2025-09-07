<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <style>
        .gradient-custom-3 {
            background: #84fab0;
            background: -webkit-linear-gradient(to right, rgba(132, 250, 176, 0.5), rgba(143, 211, 244, 0.5));
            background: linear-gradient(to right, rgba(132, 250, 176, 0.5), rgba(143, 211, 244, 0.5))
        }

        .gradient-custom-4 {
            background: #84fab0;
            background: -webkit-linear-gradient(to right, rgba(132, 250, 176, 1), rgba(143, 211, 244, 1));
            background: linear-gradient(to right, rgba(132, 250, 176, 1), rgba(143, 211, 244, 1))
        }
    </style>
    <section class="vh-100 bg-image"
             style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
        <div class="mask d-flex align-items-center h-100 gradient-custom-3">
            <div class="container h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                        <div class="card" style="border-radius: 15px;">
                            <div class="card-body p-5">
                                <h2 class="text-uppercase text-center mb-5"><@spring.message "register.title" /></h2>

                                <form action="/auth/register" method="post" class="mt-4">

                                    <#if _csrf??>
                                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                               value="${(_csrf.token)!'csrf-token'}"/>
                                    </#if>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="form3Example1cg"><@spring.message "register.first_name" /></label>
                                        <@spring.formInput "userDto.name" 'id="form3Example1cg" class="form-control form-control-lg"' />
                                        <@spring.showErrors "<br>" "error text-danger" />
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="form3Example2cg"><@spring.message "register.last_name" /></label>
                                        <@spring.formInput "userDto.surname" 'id="form3Example2cg" class="form-control form-control-lg"' />
                                        <@spring.showErrors "<br>" "error text-danger" />
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="form3Example0cg"><@spring.message "register.phone" /></label>
                                        <@spring.formInput "userDto.phoneNumber" 'id="form3Example0cg" class="form-control form-control-lg"' />
                                        <@spring.showErrors "<br>" "error text-danger" />
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="form3Example3cg"><@spring.message "register.email" /></label>
                                        <@spring.formInput "userDto.email" 'id="form3Example3cg" class="form-control form-control-lg"' />
                                        <@spring.showErrors "<br>" "error text-danger" />
                                    </div>

                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <label class="form-label" for="form3Example4cg"><@spring.message "register.password" /></label>
                                        <@spring.formInput "userDto.password" 'id="form3Example4cg" class="form-control form-control-lg"' />
                                        <@spring.showErrors "<br>" "error text-danger" />
                                    </div>

                                    <div class="mb-4">
                                        <label class="form-label"><@spring.message "register.account_type" /></label>
                                        <div>
                                            <@spring.formRadioButtons "userDto.accountType",
                                            {"applicant": springMacroRequestContext.getMessage("register.applicant"),
                                            "employer": springMacroRequestContext.getMessage("register.employer")},
                                            "",
                                            'class="form-check-input" inline="true"' />
                                            <@spring.showErrors "<br>" "error text-danger" />
                                        </div>
                                    </div>

                                    <div class="d-flex justify-content-center">
                                        <button type="submit" class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">
                                            <@spring.message "register.submit" />
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</@main.layout>