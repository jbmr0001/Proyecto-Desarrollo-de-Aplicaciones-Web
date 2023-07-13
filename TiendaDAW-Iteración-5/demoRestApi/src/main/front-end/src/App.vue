<template>
  <ClubHeader text="Prueba de la API"/>
  <ul class="list-group" id="idUsuarios">
    <li class="list-group-item" v-for="l in usuarios" :key="l.email">
      <strong>{{ l.email }}</strong> : {{ l.contraseña }} {{ l.nombre }} {{ l.direccion }}
      {{ l.tarjeta }} {{ l.admin }}
    </li>
  </ul>

  <h2>Alta de Usuario</h2>
  <form v-on:submit.prevent='alta'>
    <span class="error">{{ errores.email }}</span>
    <span class="error">{{ errores.contraseña }}</span>
    <div>
      <input placeholder="Email" class="form-control" v-model="usuario.email">
    </div>
    <input type="text" v-model='usuario.contraseña' name="contraseña" placeholder="Contraseña" class="form-control">
    <input type="text" v-model='usuario.nombre' name="nombre" placeholder="Nombre" class="form-control">
    <input type="text" v-model='usuario.direccion' name="direccion" placeholder="Direccion" class="form-control">
    <input type="text" v-model='usuario.tarjeta' name="tarjeta" placeholder="Tarjeta" class="form-control">
    <input type="text" v-model='usuario.admin' name="admin" placeholder="Admin" class="form-control">
    <input type="submit" value="Insertar Usuario">
  </form>

  <h2>Modificar Usuario</h2>
  <form v-on:submit.prevent='modifica'>
    <span class="error">{{ errores.email }}</span>
    <span class="error">{{ errores.contraseña }}</span>
    <div>
      <input placeholder="Email" class="form-control" v-model="usuario.email">
    </div>
    <div>
      <input type="text" v-model='usuario.contraseña' name="contraseña" placeholder="Contraseña" class="form-control">
    </div>
    <input type="text" v-model='usuario.nombre' name="nombre" placeholder="Nombre" class="form-control">
    <input type="text" v-model='usuario.direccion' name="direccion" placeholder="Direccion" class="form-control">
    <input type="text" v-model='usuario.tarjeta' name="tarjeta" placeholder="Tarjeta" class="form-control">
    <input type="text" v-model='usuario.admin' name="admin" placeholder="Admin" class="form-control">

    <input type="submit" value="Modificar Usuario">
  </form>

  <h2>Borrar Usuario</h2>
  <form v-on:submit.prevent='borra'>
    <span class="error">{{ errores.email }}</span>
    <span class="error">{{ errores.contraseña }}</span>
    <div>
      <input placeholder="Email" class="form-control" v-model="usuario.email">
    </div>
    <input type="submit" value="Borrar Usuario">
  </form>

</template>
<script>
import ClubHeader from "./components/commons/ClubHeader.vue";
import {UsuariosDAOfetch} from "./services/usuarios.services";

const usuariosDAO = new UsuariosDAOfetch('http://localhost:8080/TiendaDAW-1.0-SNAPSHOT/api/usuarios');
export default {
  components: {
    ClubHeader
  },
  provide: {
    usuariosDAO
  },
  mounted() {
    this.cargaUsuarios()
  },
  data() {
    return {
      usuarios: [],
      usuario: {},
      errores: {}
    }
  },
  methods: {
    cargaUsuarios() {
      //AJAX request: Promise version
      //TODO catch api access errors
      usuariosDAO.buscaTodos()
          .then(data => {
            this.usuarios = data
            this.usuario = {}
            this.errores = {}
          })
    },
    async alta() {
      if (this.validaUsuario()) {
        console.log(this.usuario.email)
        if (await usuariosDAO.crea(this.usuario)) {
          this.cargaUsuarios();
        }
      }
    },

    async modifica() {
      if (this.validaUsuario()) {
        console.log(this.usuario.email)
        if (await usuariosDAO.modifica(this.usuario)) {
          this.cargaUsuarios();
        }
      }
    },

    async borra() {
      if (this.validaUsuario()) {
        this.usuario.contraseña="a" //Para evitar la validaciónde contraseña
        console.log(this.usuario.email)
        if (await usuariosDAO.borra(this.usuario)) {
          this.cargaUsuarios();
        }
      }
    },
    validaUsuario() {
      let valido = true
      this.errores = {}

      if (!this.usuario.email) {
        this.errores.email = "Debe introducir un email"
        valido = false;
      } else {
        if (this.usuario.email.length < 4 || this.usuario.email.length > 100) {
          this.errores.email = "El email debe tener una logitud entre 4 y 100 caracteres"
          valido = false;
        }
        if (!(/^.+@.+$/.test(this.usuario.email))) {
          this.errores.email = "Formato de email inválido. Formato válido (...@...)"
          valido = false;
        }
      }
      ;

      return valido
    },
  },

}
</script>
<style scoped>
.error {
  color: red;
}
</style>
