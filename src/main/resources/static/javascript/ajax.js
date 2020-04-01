$(document).ready(function(){

    /**
    *    Returns a dynamically created alert-box
    *
    *    @param {string} type - default|warning|danger|info|success
    *    @param {string} body - content of alert box
    *    @returns DOM Object
    */
    function newAlertBox(type, body) {
        let alertBox   = $('<div class="alert-box alert-'+type+'"></div>');
        let alertBody  = $('<span>'+body+'</span>');
        let alertClose = $('<span><a class="alertBox-close"><i class="fa fa-times"></i></a></span>');

        alertBox.append(alertBody);
        alertBox.append(alertClose);

        return alertBox;
    }

    $('.newPostBtn').click(function(){
        if(!$(this).hasClass('show')){
            $('.shadow').hide();
            $(this).removeClass('hide');
            $(this).addClass('show');

            $(this).html('New post <i class="fa fa-plus"></i>');
        } else {
            $('.shadow').css('display','flex');
            $(this).removeClass('show');
            $(this).addClass('hide');

            $(this).html('Cancel <i class="fa fa-times"></i>');
        }
    });

    $(document).on('click', '.alert-box .alertBox-close', function() {
        let this_ = this;
        $(this).parents('.alert-box').fadeOut(function(){
            $(this_).parents('.alert-box').remove();
        });
    });

    $('.inputHolder > input').focus(function(){
        let sibling = $(this).siblings('.inputErrorBox');
        if(sibling.children().length > 0){
            sibling.show();
        }
    });

    $('.inputHolder > input').focusout(function(){
        let sibling = $(this).siblings('.inputErrorBox');
        sibling.hide();
    });

    $('.loginForm').submit(function(event){
        event.preventDefault();
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/login',
            type:'POST',
            data: getFormData($(this)),
            dataType: 'json',
            success: function(data){
                console.log(data);
                let response = data.response;
                let el = $('.loginForm .responseBox');
                if(response == "err#noacc" || response == "err#wuop")
                    el.html(newAlertBox('danger', 'wrong username or password'));
                else if (response == "err#active")
                    el.html(newAlertBox('warning', 'A session is already active.'));
                else if (response == "success") {
                    el.html(newAlertBox('success', 'You will be redirected in a moment ...'));
                    setTimeout(function(){
                        window.location.href = "/";
                    }, 2000);
                }

            },
            error: function(data){ console.log("ERR:" + JSON.stringify(data)); }
        });
    });

    $('.registerForm').submit(function(event){
        event.preventDefault();

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/register',
            type: 'POST',
            data: getFormData($(this)),
            dataType: 'json',
            success: function(data){
                console.log(data);
                if(data.hasOwnProperty('error')){
                    if(data.error == "err#sess")
                        $('.responseBox').html(newAlertBox('danger', 'There\'s already an active session.'));

                    if(data.error == "err#reg")
                        $('.responseBox').html(newAlertBox('warning', 'Something went wrong. Contact web-admin!'));

                    return;
                }

                if(data.hasOwnProperty('counter')){
                    if(parseInt(data.counter) == 0){
                        $('.responseBox').html(newAlertBox('success', 'Account created! You will be redirected in a moment ...'));

                        setTimeout(function(){
                            window.location.href = "/";
                        }, 2000);

                        return;
                    }
                }

                // handle input errors
                $.each(data, function(key, value){

                    // skip when key is counter
                    // since there are no input fields with that name
                    if(key == 'counter')
                        return true;

                    let errors = value.replace(/((\[)|(\s))(err#)?|(\])/gi, "").split(',');

                    let input = $('input#'+key);
                    let inErrBox = input.siblings('.inputErrorBox');
                    inErrBox.html("");

                    if(errors[0].length > 0){
                        if(input.hasClass('success'))
                            input.removeClass('success');

                        if(!input.hasClass('error'))
                            input.addClass('error')

                        let ul = $('<ul></ul>');
                        for (var i = 0; i < errors.length; i++) {
                            ul.append('<li>'+registerFormErrors[key][errors[i]]+'</li>');
                        }
                        inErrBox.append(ul);
                        inErrBox.show();
                    } else {
                        if(input.hasClass('error'))
                            input.removeClass('error');

                        if(!input.hasClass('success'))
                            input.addClass('success');

                        inErrBox.hide();
                    }
                });
            },
            error: function(data){ console.log("ERR:" + JSON.stringify(data)); }
        });
    });

    /**
        Parses form data into a JSON object
    */
    function getFormData(element){
        let formData = {};
        $.each(element.serializeArray(), function(key, obj){
            formData[obj.name] = obj.value;
        });
        return JSON.stringify(formData);
    }
});
