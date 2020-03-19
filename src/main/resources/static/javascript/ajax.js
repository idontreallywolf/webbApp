$(document).ready(function(){

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
            success: function(data){ console.log(data); },
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
            success: function(data){ console.log(data); },
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
