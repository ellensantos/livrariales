       // Validar senha
    function validarSenha(){
        var senha = document.getElementById("senha").value;
        var confSenha = document.getElementById("confirmaSenha").value;

        if(senha != confSenha){
            alert("Senhas diferentes, preencha novamente e confirme a senha!");
            document.getElementById("senha").value = "";
            document.getElementById("confirmaSenha").value = "";
        }
    }
