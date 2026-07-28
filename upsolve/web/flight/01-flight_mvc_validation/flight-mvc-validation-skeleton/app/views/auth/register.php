<?php
function e($value) {
  if ($value == null) {
    $value = '';
  }

  return htmlspecialchars($value, ENT_QUOTES, 'UTF-8');
}

function get_error($errors, $field) {
  if (isset($errors[$field])) {
    return $errors[$field];
  }

  return '';
}

function get_value($values, $field) {
  if (isset($values[$field])) {
    return $values[$field];
  }

  return '';
}

function cls_invalid($errors, $field) {
  $message = get_error($errors, $field);

  if ($message !== '') {
    return 'is-invalid';
  }

  return '';
}
?>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Inscription</title>
  <link rel="stylesheet" href="/flight-mvc-validation-skeleton/public/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card">
        <div class="card-header text-center"><h4>Inscription utilisateur</h4></div>
        <div class="card-body">

          <?php if (!empty($success)) { ?>
            <div class="alert alert-success">Inscription réussie ✅</div>
          <?php } ?>

          <form id="registerForm" method="post" action="/register" novalidate>
            <div id="formStatus" class="alert d-none"></div>

            <div class="mb-3">
              <label for="nom" class="form-label">Nom</label>
              <input id="nom" name="nom" class="form-control <?php echo cls_invalid($errors,'nom'); ?>" value="<?php echo e(get_value($values, 'nom')); ?>">
              <div class="invalid-feedback" id="nomError"><?php echo e(get_error($errors, 'nom')); ?></div>
            </div>

            <div class="mb-3">
              <label for="prenom" class="form-label">Prénom</label>
              <input id="prenom" name="prenom" class="form-control <?php echo cls_invalid($errors,'prenom'); ?>" value="<?php echo e(get_value($values, 'prenom')); ?>">
              <div class="invalid-feedback" id="prenomError"><?php echo e(get_error($errors, 'prenom')); ?></div>
            </div>

            <div class="mb-3">
              <label for="email" class="form-label">Email</label>
              <input id="email" name="email" class="form-control <?php echo cls_invalid($errors,'email'); ?>" value="<?php echo e(get_value($values, 'email')); ?>">
              <div class="invalid-feedback" id="emailError"><?php echo e(get_error($errors, 'email')); ?></div>
            </div>

            <div class="mb-3">
              <label for="password" class="form-label">Mot de passe</label>
              <input id="password" name="password" type="password" class="form-control <?php echo cls_invalid($errors,'password'); ?>">
              <div class="invalid-feedback" id="passwordError"><?php echo e(get_error($errors, 'password')); ?></div>
            </div>

            <div class="mb-3">
              <label for="confirm_password" class="form-label">Confirmation</label>
              <input id="confirm_password" name="confirm_password" type="password" class="form-control <?php echo cls_invalid($errors,'confirm_password'); ?>">
              <div class="invalid-feedback" id="confirmPasswordError"><?php echo e(get_error($errors, 'confirm_password')); ?></div>
            </div>

            <div class="mb-3">
              <label for="telephone" class="form-label">Téléphone</label>
              <input id="telephone" name="telephone" class="form-control <?php echo cls_invalid($errors,'telephone'); ?>" value="<?php echo e(get_value($values, 'telephone')); ?>">
              <div class="invalid-feedback" id="telephoneError"><?php echo e(get_error($errors, 'telephone')); ?></div>
            </div>

            <button class="btn btn-primary w-100" type="submit">S'inscrire</button>
          </form>

          <script src="/flight-mvc-validation-skeleton/public/js/validation-ajax.js" defer></script>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
