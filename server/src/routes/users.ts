import express from 'express'
import { UserService } from '../services/userService'

const router = express.Router()
const userService = new UserService()

router.get('/', async (_req, res) => {
  const users = await userService.getAllUsers()
  res.json(users)
})

router.get('/:id', async (req, res) => {
  const userId = +req.params.id;
  const user = await userService.getUserById(userId);

  if (user) {
    res.json(user);
  } else {
    res.status(404).json({ message: 'User not found' });
  }
});

router.post('/register', async (req, res) => {
  try {
    await userService.register(req.body);
    res.status(201).send('Successfully created a new user');
  } catch (e) {
    if (e instanceof Error) {
      res.status(400).send(e.message)
    }
    res.status(500).send('An unexpected error occurred.')
  }
});

router.post('/login', async (req, res) => {
  try {
    const foundUser = await userService.login(req.body);
    res.status(200).send(foundUser);
  } catch (e) {
    if (e instanceof Error) {
      res.status(400).send(e.message)
    }
    res.status(500).send('An unexpected error occurred.')
  }
});

export default router;
