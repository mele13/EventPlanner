import { config } from 'dotenv';
import path from 'path';
import { DataSource } from 'typeorm';

const envPath = path.resolve(__dirname, '../../../.env');

config(); // Loading .env config
config({ path: envPath });

// Connection configuration
const AppDataSource = new DataSource({
  type: "postgres",
  port: parseInt(process.env.DB_PORT || '5432', 10),
  database: process.env.DB_DBNAME,
  username: process.env.DB_USER,
  password: process.env.DB_PASS,
  "migrations": [
    "src/sql/migrations/*.ts"
  ],
  "entities": [
    "src/models/*.ts"
  ],
})

export { AppDataSource }
