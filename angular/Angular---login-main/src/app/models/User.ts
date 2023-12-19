export class User {
    constructor(
        public usuario_id: number,
        public nombreusuario: string,
        public clave: string,
        public nombre: string,
        public apellido: string,
        public token: string
    ){}
}