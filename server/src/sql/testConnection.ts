import { db } from "./db";

async function testConnection() {
  try {
    const result = await db.one('SELECT $1 AS value', 123);
    console.log('Successful connection:', result.value);
  } catch (e) {
    if (e instanceof Error) {
      console.error('Error connecting to the database:', e.message);
    } else {
      console.error('Error connecting to the database');
    }
  } finally {
    db.$pool.end(); // Close connection
  }
}

testConnection();