<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Créer un compte</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
  <style>
    body {
      background: linear-gradient(to right, #43cea2, #185a9d);
      height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }
    .register-container {
      background-color: white;
      padding: 40px 30px;
      border-radius: 10px;
      box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
      width: 100%;
      max-width: 400px;
    }
    .register-container h2 {
      text-align: center;
      font-weight: bold;
      margin-bottom: 25px;
    }
    .form-group i {
      position: absolute;
      top: 10px;
      left: 15px;
      color: #aaa;
    }
    .form-control {
      padding-left: 40px;
    }
    .btn-success {
      background-color: #28a745;
      border: none;
    }
    .btn-success:hover {
      background-color: #218838;
    }
  </style>
</head>
<body>

  <div class="register-container">
    <h2><i class="fa fa-user-plus"></i> Créer un compte</h2>

    <form action="register" method="post">
      <div class="form-group position-relative mb-3">
        <i class="fa fa-user"></i>
        <input type="text" name="username" class="form-control" placeholder="Nom d'utilisateur" required>
      </div>

      <div class="form-group position-relative mb-3">
        <i class="fa fa-lock"></i>
        <input type="password" name="password" class="form-control" placeholder="Mot de passe" required>
      </div>

      <div class="form-group position-relative mb-4">
        <i class="fa fa-user-tag"></i>
        <select name="role" class="form-control" required>
          <option value="">Choisir un rôle</option>
          <option value="USER">Apprenant</option>
          <option value="ADMIN">Admin</option>
        </select>
      </div>

      <button type="submit" class="btn btn-success w-100">Créer le compte</button>
    </form>
  </div>

</body>
</html>
