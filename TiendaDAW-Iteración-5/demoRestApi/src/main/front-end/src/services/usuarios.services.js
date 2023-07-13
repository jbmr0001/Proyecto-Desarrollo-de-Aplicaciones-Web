/** Class Service samples: DAO implementations */
export class UsuariosDAOfetch {

    /** Configure DAO with REST api URL
     * @pre requires CORS enabled on REST API
     * @param apiurl REST api URL*/
    constructor(apiurl) {
        this.srvUrl = apiurl;
        this.respuestaValida=false; //status of last ajax request
    }
    buscaTodos() {
        return fetch(this.srvUrl)
            .then (response => this.comprobarRespuesta(response) )
            .then (response => this.devolverRespuesta(response) );
    }

    crea(usuario) {
        console.log(this.srvUrl)
        return fetch(this.srvUrl, {
            method: 'POST',
            body: JSON.stringify(usuario),
            headers: {
                'Content-type': 'application/json',
                'accept': 'application/json'
            }
        })
            .then (response => this.comprobarRespuesta(response) )
            .then (response => this.devolverRespuesta(response) );
    }

    modifica(usuario) {
        console.log(this.srvUrl+"/"+usuario.email)
        return fetch(this.srvUrl+"/"+usuario.email, {
            method: 'PUT',
            body: JSON.stringify(usuario),
            headers: {
                'Content-type': 'application/json',
                'accept': 'application/json'
            }
        })
            .then (response => this.comprobarRespuesta(response) )
            .then (response => this.devolverRespuesta(response) );
    }
    borra(usuario) {
        console.log(this.srvUrl+"/"+usuario.email)
        return fetch(this.srvUrl+"/"+usuario.email, {
            method: 'DELETE'
        })
            .then (response => this.comprobarRespuestaBorrado(response) )
            .then (response => this.devolverRespuestaBorrado(response) );
    }
    /** Saves response status and returns object data*/
    comprobarRespuestaBorrado(response) {
        this.respuestaValida=response.ok;
        //TODO check network errors
        return response;
    }
    devolverRespuestaBorrado () {
        //Resolves or reject promise with response data
        if (!this.respuestaValida) {
            //send validation errors
            //Rejects promise, forces catch response in DAO
            return Promise.reject();
        }
        return {};
    }

    comprobarRespuesta(response) {
        this.respuestaValida=response.ok;
        //TODO check network errors
        return response.json();
    }
    devolverRespuesta (json) {
        //Resolves or reject promise with response data
        if (!this.respuestaValida) {
            //send validation errors
            //Rejects promise, forces catch response in DAO
            return Promise.reject(json);
        }
        return json;
    }
} //End ClientesDAOfetch