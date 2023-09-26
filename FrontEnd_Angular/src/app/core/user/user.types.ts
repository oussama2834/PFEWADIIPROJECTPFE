export interface User {
    //Edited
    id: number;
    name: string;
    email: string;
    password: string;
    tele: number;
    address: string;
    roles: Role[];
    sexe: string;
    position: string;
    agreements: string;
    departement: string;
    avatar?: string;
    status?: string;
}

// Added
export interface Role {
    id: number;
    name : string
}
