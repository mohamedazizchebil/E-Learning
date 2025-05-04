<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="entities.User" %>
<%@ page import="entities.Formation" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>E-learning - Admin</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" />
  <style>
  body {
  background-color: #f4f8fc;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.navbar {
  background-color: #007bff;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

.navbar-brand {
  font-weight: bold;
  font-size: 1.7rem;
  color: white;
}

.navbar-nav .nav-link {
  font-weight: 500;
  color: white !important;
}

.navbar-nav .nav-link:hover {
  color: #dbeeff !important;
  text-decoration: underline;
}

.alert-info {
  background-color: #d0e7ff;
  border-left: 6px solid #007bff;
}

.search-form .form-control {
  border: 1px solid #ced4da;
  border-right: none;
}

.search-form .btn {
  background-color: #007bff;
  border: none;
}

.search-form .btn:hover {
  background-color: #0056b3;
}

.card {
  border-radius: 10px;
  border: none;
  overflow: hidden;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.card-header {
  background-color: #0d6efd;
  color: white;
  font-weight: bold;
  font-size: 1.25rem;
}

.table thead {
  background-color: #007bff;
  color: white;
}

.table-striped tbody tr:nth-of-type(odd) {
  background-color: #f0f8ff;
}

.btn-warning {
  background-color: #ffc107;
  border: none;
}

.btn-warning:hover {
  background-color: #e0a800;
}

.btn-danger {
  background-color: #dc3545;
  border: none;
}

.btn-danger:hover {
  background-color: #c82333;
}

.no-data {
  font-style: italic;
  color: #6c757d;
  text-align: center;
}

  </style>
</head>
<body>

<%
  User utilisateur = (User) session.getAttribute("utilisateur");
%>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark">
  <div class="container">
    <a class="navbar-brand" href="admin">E-Learning</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link" href="espaceFormateur"><i class="fa fa-chalkboard-teacher"></i> Formateurs</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="add"><i class="fa fa-plus-circle"></i> Ajouter Formation</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="logout"><i class="fa fa-sign-out-alt"></i> Déconnexion</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<!-- Contenu principal -->
<div class="container mt-4">

  <div class="alert alert-info d-flex justify-content-between align-items-center shadow-sm">
    <span>👋 Bienvenue <strong><%= utilisateur.getUsername() %></strong> dans votre espace administrateur</span>
    
  </div>

  <!-- Barre de recherche -->
  <form action="search" method="get" class="search-form">
    <div class="input-group shadow">
      <input type="text" name="mc" class="form-control" placeholder="Rechercher une formation..." value="${mc}">
      <button type="submit" class="btn btn-primary">
        <i class="fa fa-search"></i> Rechercher
      </button>
    </div>
  </form>

  <!-- Tableau des formations -->
  <div class="card shadow mt-4">
    <div class="card-header">
      <i class="fa fa-list"></i> Liste des Formations
    </div>
    <div class="card-body p-0">
      <table class="table table-striped table-hover mb-0">
        <thead>
          <tr>
            <th>ID</th>
            <th>Titre</th>
            <th>Description</th>
            <th>Formateur</th>
            <th class="text-center">Actions</th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${empty formations}">
              <tr>
                <td colspan="5" class="no-data py-4">Aucune formation disponible.</td>
              </tr>
            </c:when>
            <c:otherwise>
              <c:forEach var="f" items="${formations}">
                <tr>
                  <td>${f.id}</td>
                  <td>${f.titre}</td>
                  <td>${f.description}</td>
                  <td>
                    <c:choose>
                      <c:when test="${not empty f.formateur}">
                        ${f.formateur.nom}
                      </c:when>
                      <c:otherwise>
                        <span class="text-muted">Non assigné</span>
                      </c:otherwise>
                    </c:choose>
                  </td>
                  <td class="text-center">
                    <a href="update?id=${f.id}" class="btn btn-warning btn-sm" title="Modifier">
                      <i class="fa fa-edit"></i>
                    </a>
                    <a href="delete?id=${f.id}" class="btn btn-danger btn-sm" title="Supprimer"
                       onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette formation ?');">
                      <i class="fa fa-trash-alt"></i>
                    </a>
                  </td>
                </tr>
              </c:forEach>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
    </div>
  </div>

</div>

<!-- JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
