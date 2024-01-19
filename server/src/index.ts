import express from 'express'
import diaryRouter from './routes/diaries'
import userRouter from './routes/users'
import { AppDataSource } from './sql/db'

const app = express()
app.use(express.json()) // Middleware - transforms req.body into json

const PORT = process.env.PORT || 3001

// Establish database connection
AppDataSource.initialize()
  .then(() => {
    console.log('Connected to the database');

    app.get('/ping', (_req, res) => {
      console.log('ping done ' + new Date().toLocaleDateString())
      res.send('pong')
    }) // endpoint
    
    app.use('/api/diaries', diaryRouter)
    app.use('/api/users', userRouter)
    
    app.listen(PORT, () => {
      console.log(`Server running on port ${PORT}`)
    })

  }).catch((e: any) => {
    if (e instanceof Error) {
      console.error('Error connecting to the database: ', e)
    } else {
      console.error('Error connecting to the database')
    }
  })
