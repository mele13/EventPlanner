// import express from 'express'
// // import * as diaryServices from '../services/diaryServices'
// // import toNewDiaryEntry from '../utils'

// const router = express.Router()

// router.get("/users", (req, res) => {
//   // here we will have logic to return all users
// })

// router.get("/users/:id", (req, res) => {
//   // here we will have logic to return user by id
// })

// router.post("/users", (req, res) => {
//   // here we will have logic to save a user
// })

// router.put("/users/:id", (req, res) => {
//   // here we will have logic to update a user by a given user id
// })

// router.delete("/users/:id", (req, res) => {
//   // here we will have logic to delete a user by a given user id
// })

// // router.get('/', (_req, res) => {
// //   res.send(diaryServices.getEntriesWithoutSensitiveInfo())
// // })

// // router.get('/:id', (req, res) => {
// //   const diary = diaryServices.findById(+req.params.id)
// //   // const diary = diaryServices.findById(Number(req.params.id))
// //   return (diary != null)
// //     ? res.send(diary)
// //     : res.sendStatus(404)
// //   // res.send(diary?.weather)
// // })

// // router.post('/', (req, res) => {
// //   try {
// //     const newDiaryEntry = toNewDiaryEntry(req.body)

// //     const addedDiaryEntry = diaryServices.addDiary(newDiaryEntry)

// //     res.json(addedDiaryEntry)
// //   } catch (e) {
// //     if (e instanceof Error) {
// //       res.status(400).send(e.message)
// //     }
// //     res.status(400).send('An unexpected error occurred.')
// //   }
// // })

// export default router
