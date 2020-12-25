$('document').ready(function() {

/* Edit User Modal Form*/
		$('.table .btn-info').on('click',function(event){
    		event.preventDefault();
    		var href= $(this).attr('href');
    		console.log(href);
    		$.get(href, function(user, status){
    			$('#idEdit').val(user.id);
    			$('#firstnameEdit').val(user.firstname);
    			$('#lastnameEdit').val(user.lastname);
    			$('#emailEdit').val(user.email);
    			$('#usernameEdit').val(user.username);
    			$('#passwordEdit').val(user.password);

                var idUser=document.getElementById("idEdit").value;
                var table = document.getElementById("table");
                var totalRows = document.getElementById("table").rows.length;
                var totalCol = 8; // enter the number of columns in the table minus 1 (first column is 0 not 1)
                var firstCell;
                //var arraysRoles = [];
                var abort, active;
                var checker = false;
                    //To display all values
                for (var x = 0; x < totalRows && !abort; x++) {
	                for (var y = 0; y <= totalCol; y++) {
	                        var check= table.rows[x].cells[y].innerHTML
		                    if (check == idUser) {
			                     firstCell = table.rows[x].cells[7].innerHTML; // take the value of the Valid cell for activation of the user from admin

			                     //arraysRoles = table.rows[x].cells[8].innerHTML;
			                     //console.log(arraysRoles);

			                     $('#enabledEdit').val(firstCell);

                                    /*if (firstCell != checker) {
                                        console.log(firstCell);
                                        active = true;
                                        $('#enabledEdit').val(active);
                                    }*/
			                     abort = true;
			                    //break;
		                    }
	                }
                }


    			//$('#enabledEdit').val(user.enabled);
    			$('#rolesEdit').val(user.roles);
    		});
    		$('#editModal').modal();
    	});

	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(user, status){
			$('#txtIdDetails').val(user.id);
            $('#txtFirstnameDetails').val(user.firstname);
            $('#txtLastnameDetails').val(user.lastname);
            $('#txtEmailDetails').val(user.email);
            $('#txtUsernameDetails').val(user.username);
            $('#txtConfirmationTokenDetails').val(user.confirmationToken);

                var idUser=document.getElementById("txtIdDetails").value;
                var table = document.getElementById("table");
                var totalRows = document.getElementById("table").rows.length;
                var totalCol = 8; // enter the number of columns in the table minus 1 (first column is 0 not 1)
                var firstCell;
                var abort, active;
                var checker = false;
                    //To display all values
                for (var x = 0; x < totalRows && !abort; x++) {
	                for (var y = 0; y <= totalCol; y++) {
	                        var check= table.rows[x].cells[y].innerHTML
		                    if (check == idUser) {
			                    //alert(table.rows[x].cells[1].innerHTML);
			                     firstCell = table.rows[x].cells[7].innerHTML; // take the value of the Valid cell for activation of the user from admin
			                     $('#txtValidDetails').val(firstCell);
                                    /*if (firstCell != checker) {
                                        console.log(firstCell);
                                        active = true;
                                        $('#enabledEdit').val(active);
                                    }*/
			                     abort = true;
			                    //break;
		                    }
	                }
                }
            //$('#txtValidDetails').val(r);
		});			
		$('#detailsModal').modal();		
	});	

	/*Delete Modal*/
	$('.table #deleteButton').on('click',function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteModal #delRef').attr('href', href);
		$('#deleteModal').modal();		
	});

/* Update Password Modal Form*/
	$('.table .btn-warning').on('click',function(event) {
    	event.preventDefault();
    	var href= $(this).attr('href');
    	$.get(href, function(user, status){
    		$('#idEdit2').val(user.id);
    		$('#firstnameEdit2').val(user.firstname);
            $('#lastnameEdit2').val(user.lastname);
            $('#emailEdit2').val(user.email);
            $('#usernameEdit2').val(user.username);
            $('#passwordEdit2').val(user.password);
            var idUser=document.getElementById("enabledEdit2").value;
                            var table = document.getElementById("table");
                            var totalRows = document.getElementById("table").rows.length;
                            var totalCol = 8; // enter the number of columns in the table minus 1 (first column is 0 not 1)
                            var firstCell;
                            var abort, active;
                            var checker = false;
                                //To display all values
                            for (var x = 0; x < totalRows && !abort; x++) {
            	                for (var y = 0; y <= totalCol; y++) {
            	                        var check= table.rows[x].cells[y].innerHTML
            		                    if (check == idUser) {
            			                    //alert(table.rows[x].cells[1].innerHTML);
            			                     firstCell = table.rows[x].cells[7].innerHTML; // take the value of the Valid cell for activation of the user from admin
            			                     $('#enabledEdit2').val(firstCell);
                                                /*if (firstCell != checker) {
                                                    console.log(firstCell);
                                                    active = true;
                                                    $('#enabledEdit').val(active);
                                                }*/
            			                     abort = true;
            			                    //break;
            		                    }
            	                }
                            }

            //$('#enabledEdit2').val(user.enabled);
            $('#rolesEdit2').val(user.roles);
    	});
    	$('#editPasswordModal').modal();
    });

});