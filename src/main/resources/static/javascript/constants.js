const registerFormErrors = {
    "firstname": {
        "minLen": "Must be atleast 4 characters long.",
        "maxLen": "Must be atmost 20 characters long.",
        "sym":  "Only characters <b>a-z A-Z</b> are allowed."
    },
    "lastname": {
        "minLen": "Must be atleast 4 characters long.",
        "maxLen": "Must be atmost 20 characters long.",
        "sym":  "Only characters <b>a-z A-Z</b> are allowed."
    },
    "username": {
        "minLen": "Must be atleast 4 characters long.",
        "maxLen": "Must be atmost 30 characters long.",
        "sym":  "Only characters <b>a-z A-Z 0-9</b> are allowed.",
        "taken": "This username is already taken."
    },
    "password": {
        "cnfrm": "Password does not match confirmation",
        "minLen": "Must be atleast 8 characters long.",
        "maxLen": "Must be atmost 60 characters long.",
        "badSec": "Atleast 1 of each <b>a-z A-Z 0-9</b> must be used."
    },
    "email": {
        "len": "Email length cannot be shorter than 6",
        "inv": "invalid email format",
        "taken": "This email is already taken."
    }
}
