import { Entity, PrimaryGeneratedColumn, Column } from "typeorm"

@Entity()
export class User {
    @PrimaryGeneratedColumn()
    id!: number

    @Column("text")
    username!: string

    @Column("text")
    email!: string

    @Column("text")
    password!: string

    @Column("text")
    role!: string
}