import express from 'express'
const app = express()
app.use(express.json()) // Middleware - transforms req.body into json

const PORT = 3000

app.get('/ping', (_req, res) => {
  console.log('ping done')
  res.send('pong')
}) // endpoint

app.listen(PORT, () => {
  console.log(`server running on port ${PORT}`)
})
