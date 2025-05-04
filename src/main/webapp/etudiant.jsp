<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.Formation" %>
<%@ page import="entities.User" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-learning - Etudiant</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" />
    <style>
        body {
            background-color: #f8f9fa;
        }

        .navbar {
            background-color: #007bff;
        }

        .navbar-brand {
            font-weight: bold;
            font-size: 1.5rem;
        }

        .navbar a {
            color: #ffffff !important;
            font-weight: 600;
        }

        .table-hover tbody tr:hover {
            background-color: #e3f2fd;
        }

        .search-form {
            max-width: 600px;
            margin: auto;
        }

        .table-custom thead {
            background-color: #007bff;
            color: white;
        }

        .table-custom td, .table-custom th {
            vertical-align: middle;
        }

        .add-btn {
            display: flex;
            justify-content: flex-end;
        }

        .alert-info {
            border-radius: 8px;
        }

        .card-header {
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }

        .card-body {
            padding: 0;
        }

        .btn-info {
            background-color: #17a2b8;
            border-color: #17a2b8;
        }

        .btn-info:hover {
            background-color: #138496;
            border-color: #117a8b;
        }

        .pagination {
            justify-content: center;
        }

        .table-custom td,
        .table-custom th {
            vertical-align: middle;
            text-align: center;
        }

    </style>
</head>
<body>

<%
  User utilisateur = (User) session.getAttribute("utilisateur");
%>

    <!-- Navigation Bar -->
    <nav class="navbar navbar-expand-lg">
        <div class="container">
            <a class="navbar-brand" href="etudiant">E-Learning</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                <li class="nav-item">
    <a class="nav-link" href="MesInscriptions">Mes Inscriptions</a>
</li>
                
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Déconnexion</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    
    <c:if test="${not empty message}">
    <div class="alert alert-success">${message}</div>
</c:if>
    

    <div class="container mt-4">
        <!-- Welcome message -->
        <div class="alert alert-info d-flex justify-content-between align-items-center shadow-sm">
            <span>👋 Bienvenue <strong><%= utilisateur.getUsername() %></strong> dans votre espace étudiant</span>
        </div>

        <!-- Search Form -->
        <form action="chercher" method="get" class="search-form mb-4">
            <div class="input-group shadow-sm">
                <input type="text" name="mc" class="form-control" placeholder="Rechercher une formation..." value="${param.mc}">
                <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i> Rechercher</button>
            </div>
        </form>

        <!-- Table of Formations -->
        <div class="card shadow-sm">
            <div class="card-header">
                Liste des Formations
            </div>
            <div class="card-body">
                <table class="table table-striped table-hover table-custom mb-0">
                    <thead>
                        <tr>
                            <th>Titre</th>
                            <th>Description</th>
                            <th>Formateur</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${empty formations}">
                                <tr>
                                    <td colspan="3" class="text-center text-muted py-3">Aucune formation disponible</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${formations}" var="formation">
                                    <tr>
                                        <td>${formation.titre}</td>
                                        <td>${formation.description}</td>
                                         <td>
                    <c:choose>
                      <c:when test="${not empty formation.formateur.nom}">
                        ${formation.formateur.nom}
                      </c:when>
                      <c:otherwise>
                        <span class="text-muted">Non assigné</span>
                      </c:otherwise>
                    </c:choose>
                  </td>
                                        
                                        <td>
                                            
                                            <form action="inscrire" method="post" style="display:inline;">
                <input type="hidden" name="formationId" value="${formation.id}" />
                <input type="submit" class="btn btn-sm btn-success" value="S'inscrire" />
            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Pagination (optional) -->
        <div class="pagination mt-3">
            <!-- Add pagination controls here if needed -->
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
