import * as bcrypt from 'bcrypt';
import * as jwt from 'jsonwebtoken';
import { Repository } from "typeorm";
import { User } from "../models/User";
import { AppDataSource } from "../sql/db";

// const SECRET_KEY = 'aabbccddee';
const SECRET_KEY = process.env.SECRET_KEY || 'abece';

export class UserService {
  private userRepository: Repository<User>
  private saltRounds: number = 8

  constructor() {
    this.userRepository = AppDataSource.getRepository(User)
  }

  async getAllUsers(): Promise<User[]> {
    return await this.userRepository.find()
  }

  async getUserById(id: number): Promise<User | undefined> {
    return await this.userRepository.findOne({ where: { id } }) ?? undefined
  }

  async deleteUser(id: number): Promise<void> {
    const userToDelete = await this.userRepository.findOne({ where: { id } }) ?? undefined
    if (userToDelete) {
      await this.userRepository.remove(userToDelete)
    } else {
      throw new Error('User not found')
    }
  }

  async register(userData: Partial<User>): Promise<User> {
    const hashedPassword = await bcrypt.hash(userData.password!, this.saltRounds)
    const newUser = this.userRepository.create({
      ...userData,
      password: hashedPassword,
    })
    return await this.userRepository.save(newUser)
  }

  async login(user: Partial<User>):Promise<{ user: User, token: string }> {
    const { username, password } = user
    const foundUser = await this.userRepository.findOne({ where: { username } })

    if (!foundUser) {
      throw new Error('Incorrect username or password')
    }
    
    const isMatch = await bcrypt.compareSync(password!, foundUser.password)

    if (isMatch) {
      const token = jwt.sign({ _id: foundUser.id?.toString(), name: foundUser.username }, SECRET_KEY, {
        expiresIn: '1 day',
      })
      return { user: foundUser, token: token }
    } else {
      throw new Error('Incorrect username or password')
    }
  }
}
