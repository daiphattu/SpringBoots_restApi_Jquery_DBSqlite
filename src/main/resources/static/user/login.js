let objlg = {
    username: '',
    password: '',
    role: ''
}

$(document).ready( () =>{
    $('#loginMdl').on('click', '#loginId', (e)=>{
        objlg.username = ($('#inputUser').val());
        objlg.password = ($('#inputPassword').val());

        console.log(objlg);

        if(objlg){
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: LOGIN_ADMIN_OR_USER,
                data: JSON.stringify(objlg),
                dataType: "json",
                success: (response) => {

                },
                error: (err) => {

                }
            });
        		}
    });
});