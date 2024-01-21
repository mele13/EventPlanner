import { AppDataSource } from "./db"

async function testConnection() {
  try {
    await AppDataSource.connect()
    console.log('Successful connection')
  } catch (e) {
    if (e instanceof Error) {
      console.error('Error connecting to the database:', e.message)
    } else {
      console.error('Error connecting to the database')
    }
  } finally {
    await AppDataSource.close()
  }
}

testConnection();
