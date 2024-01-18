import { IDatabase, IMain } from 'pg-promise'
import pgPromise from 'pg-promise'
import { config } from 'dotenv';
import path from 'path';

const pgp: IMain = pgPromise();
const envPath = path.resolve(__dirname, '../../.env');

config(); // Loading .env config
config({ path: envPath });

// Connection configuration
const connectionOptions = {
  port: parseInt(process.env.DB_PORT || '5432', 10),
  database: process.env.DB_DBNAME,
  user: process.env.DB_USER,
  password: process.env.DB_PASS,
};

const db: IDatabase<{}> = pgp(connectionOptions);

export { db };
