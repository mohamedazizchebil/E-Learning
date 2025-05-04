<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entities.Formation" %>
<%@ page import="entities.User" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Mes Inscriptions</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
    <style>
        body {
            background-color: #f0f8ff; /* Bleu très clair */
        }
        .card-header {
            background-color: #007bff; /* Bleu Bootstrap */
        }
        .btn-custom {
            background-color: #339af0;
            color: white;
        }
        .btn-custom:hover {
            background-color: #1c7ed6;
            color: white;
        }
        .table thead {
            background-color: #339af0;
            color: white;
        }
        .table-striped tbody tr:nth-of-type(odd) {
            background-color: #e7f3ff;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header text-white d-flex justify-content-between align-items-center">
            <h4 class="mb-0"><i class="fas fa-graduation-cap me-2"></i>Mes Formations Inscrites</h4>
            <a href="Etudiant" class="btn btn-light btn-sm">
                <i class="fas fa-arrow-left me-1"></i>Retour
            </a>
        </div>
        <div class="card-body bg-white">

            <c:choose>
                <c:when test="${empty formationsInscrites}">
                    <div class="alert alert-warning text-center">
                        <i class="fas fa-info-circle me-2"></i>Vous n'êtes inscrit à aucune formation.
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="table-responsive">
                        <table class="table table-striped align-middle text-center">
                            <thead>
                                <tr>
                                    <th><i class="fas fa-book me-1"></i>Titre</th>
                                    <th><i class="fas fa-align-left me-1"></i>Description</th>
                                    <th><i class="fas fa-chalkboard-teacher me-1"></i>Formateur</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="formation" items="${formationsInscrites}">
                                    <tr>
                                        <td>${formation.titre}</td>
                                        <td>${formation.description}</td>
                                        <td>${formation.formateur.nom}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</div>

</body>
</html>
