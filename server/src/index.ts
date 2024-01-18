import express from 'express'

import diaryRouter from './routes/diaries'

const app = express()
app.use(express.json()) // Middleware - transforms req.body into json

const PORT = process.env.PORT || 3001

app.get('/ping', (_req, res) => {
  console.log('ping done ' + new Date().toLocaleDateString())
  res.send('pong')
}) // endpoint

app.use('/api/diaries', diaryRouter)

app.listen(PORT, () => {
  console.log(`server running on port ${PORT}`)
})
