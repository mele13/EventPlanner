import { MigrationInterface, QueryRunner, Table } from "typeorm";

export class CreateUsers1705685105327 implements MigrationInterface {

    public async up(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.createTable(new Table({
            name: "user",
            columns: [
                {
                    name: "id",
                    type: "serial",
                    isPrimary: true,
                },
                {
                    name: "username",
                    type: "text",
                    isUnique: true,
                },
                {
                    name: "email",
                    type: "text",
                    isUnique: true,
                },
                {
                    name: "password",
                    type: "text",
                },
                {
                    name: "role",
                    type: "text",
                },
            ],
        }), true)
    }

    public async down(queryRunner: QueryRunner): Promise<void> {
        await queryRunner.dropTable("user");
    }
}
