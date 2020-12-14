$('document').ready(function() {

    $('.table .btn-primary').on('click',function(event){
		event.preventDefault();
		var href= $(this).attr('href');
        $.get(href, function(user, status){
        $('#txtIdEdit').val(user.id);
        $('#firstnameEdit').val(user.firstname);
        $('#lastnameEdit').val(user.lastname);
        $('#emailEdit').val(user.email);
        $('#usernameEdit').val(user.username);
        $('#passwordEdit').val(user.password);

        var idUser=document.getElementById("txtIdEdit").value;
        var table = document.getElementById("table");
        var totalRows = document.getElementById("table").rows.length;
        var totalCol = 3; // enter the number of columns in the table minus 1 (first column is 0 not 1)
        var firstCell = true;
        var abort, active;
        var checker = false;
        //To display all values
        for (var x = 0; x < totalRows && !abort; x++) {
	        for (var y = 0; y <= totalCol; y++) {
	            var check= table.rows[x].cells[y].innerHTML
		            if (check == idUser) {
			            //alert(table.rows[x].cells[1].innerHTML);
			            firstCell = table.rows[x].cells[3].innerHTML; // take the value of the Valid cell for activation of the user from admin
			            console.log(firstCell);
			            if (firstCell != checker) {
                                 console.log(firstCell);
                                 active = true;
                                 $('#txtEnabledEditView').val(active);
                        }
			            abort = true;
			            //break;
		            }
	        }
        }
        $('#rolesEdit').val(user.roles);
        });
        $('#editModal').modal();
    });

});