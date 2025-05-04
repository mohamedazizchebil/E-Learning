<%@page import="entities.Formation"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>${operation} une Formation</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">

    <!-- Custom Style -->
    <style>
        body {
            background: linear-gradient(to right, #f1f9ff, #ffffff);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .form-box {
            background-color: #fff;
            padding: 30px;
            border-radius: 16px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 600px;
        }

        h2 {
            color: #0d6efd;
            font-weight: 600;
        }

        .btn-primary {
            background-color: #0d6efd;
            border: none;
        }

        .btn-primary:hover {
            background-color: #0b5ed7;
        }

        .form-label {
            font-weight: 500;
        }

        .input-group-text {
            background-color: #e9ecef;
        }
    </style>
</head>
<body>

<div class="form-box">
    <h2 class="text-center mb-4"><i class="fas fa-chalkboard-teacher me-2"></i>${operation} une Formation</h2>
    <form action="ajouter" method="post">
        <div class="mb-3">
            <label for="titre" class="form-label">Titre</label>
            <div class="input-group">
                <span class="input-group-text"><i class="fas fa-heading"></i></span>
                <input type="text" class="form-control" id="titre" name="titre" placeholder="Titre de la formation" value="${formation.titre}" required>
            </div>
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <div class="input-group">
                <span class="input-group-text"><i class="fas fa-align-left"></i></span>
                <input type="text" class="form-control" id="description" name="description" placeholder="Description courte" value="${formation.description}" required>
            </div>
        </div>

        <div class="mb-4">
            <label for="formateur" class="form-label">Formateur</label>
            <div class="input-group">
                <span class="input-group-text"><i class="fas fa-user-tie"></i></span>
                <select name="formateur" id="formateur" class="form-select" required>
                    <option value="" disabled selected hidden>Choisir un formateur</option>
                    <c:forEach items="${formateurs}" var="f">
                        <option value="${f.id}" <c:if test="${formation.formateur.id == f.id}">selected</c:if>>${f.nom}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="d-flex justify-content-between">
            <a href="admin" class="btn btn-outline-secondary">
                <i class="fas fa-arrow-left"></i> Retour
            </a>
            <button type="submit" class="btn btn-primary">
                 ${operation}
            </button>
        </div>

        <input type="hidden" name="id" value="${formation.id}">
    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
