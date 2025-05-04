<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.Formateur" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Gestion des Formateurs</title>

  <!-- Bootstrap 5 CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

  <!-- Custom Styles -->
  <style>
    body {
      background-color: #f8f9fa;
    }

    .navbar {
  background-color: #007bff;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  padding: 1px 0; /* Hauteur réduite */
  
}

    .navbar a {
      color: #ffffff !important;
    }

    .navbar .navbar-brand, .navbar .nav-link {
      font-weight: 600;
      margin-left: 10px;
    }

    .container {
      margin-top: 50px;
    }

    h2 {
      color: #007bff;
      font-weight: 600;
      margin-bottom: 30px;
      text-align: center;
    }

    .alert {
      border-radius: 8px;
    }

    .table {
      background-color: white;
      color: #333;
      border-radius: 10px;
      overflow: hidden;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .table th {
      background-color: #0069d9;
      color: white;
    }

    .table-striped tbody tr:nth-child(odd) {
      background-color: #f1f1f1;
    }

    .table-hover tbody tr {
      transition: background-color 0.3s ease;
    }

    .table-hover tbody tr:hover {
      background-color: #e1ecff;
    }

    .btn-sm {
      border-radius: 20px;
    }

    .btn-danger {
      background-color: #dc3545;
      border: none;
    }

    .btn-danger:hover {
      background-color: #c82333;
    }

    .btn-success {
      background-color: #28a745;
      border: none;
    }

    .btn-success:hover {
      background-color: #218838;
    }

    .btn-primary {
      background-color: #007bff;
      border: none;
    }

    .btn-primary:hover {
      background-color: #0056b3;
    }

    @media (max-width: 768px) {
      .navbar-nav {
        text-align: center;
      }

      .table th, .table td {
        padding: 10px;
      }

      h2 {
        font-size: 1.5rem;
      }
    }
  </style>
</head>
<body>

  <!-- Navbar -->
  <nav class="navbar navbar-light">
    <div class="container">
      <a class="navbar-brand" href="#">Gestion Formateurs</a>
      <div class="d-flex">
        <a class="nav-link" href="admin"><i class="fa fa-home"></i> Accueil</a>
        <a class="nav-link" href="ajout"><i class="fa fa-plus-circle"></i> Ajouter Formateur</a>
      </div>
    </div>
  </nav>

  <!-- Main Content -->
  <div class="container">

    <!-- Notification de succès -->
    <c:if test="${not empty successMessage}">
      <div class="alert alert-success mt-3 d-flex align-items-center">
        <i class="fas fa-check-circle me-2"></i>
        ${successMessage}
      </div>
    </c:if>

    <!-- Titre -->
    <h2>Liste des Formateurs</h2>


    <!-- Tableau -->
    <table class="table table-striped table-hover table-bordered shadow-sm">
      <thead>
        <tr>
          <th>Nom</th>
          <th>Email</th>
          <th>Spécialité</th>
          <th class="text-center">Actions</th>
        </tr>
      </thead>
      <tbody>
        <c:choose>
          <c:when test="${empty formateurs}">
            <tr>
              <td colspan="4" class="text-center text-muted">Aucun formateur trouvé.</td>
            </tr>
          </c:when>
          <c:otherwise>
            <c:forEach items="${formateurs}" var="formateur">
              <tr>
                <td>${formateur.nom}</td>
                <td>${formateur.email}</td>
                <td>${formateur.specialite}</td>
                <td class="text-center">
                  <a href="supprimer?id=${formateur.id}" 
                     class="btn btn-danger btn-sm" 
                     onclick="return confirm('Voulez-vous vraiment supprimer ce formateur ?');"
                     title="Supprimer">
                    <i class="fas fa-trash-alt"></i> Supprimer
                  </a>
                </td>
              </tr>
            </c:forEach>
          </c:otherwise>
        </c:choose>
      </tbody>
    </table>

  </div>

  <!-- Bootstrap JS -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
