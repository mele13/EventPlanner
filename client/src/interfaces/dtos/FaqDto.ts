export interface AnswerFaqDto {
  id: number;
  answer: string;
}

export interface QuestionFaqDto {
  id?: number;
  question: string;
}

export default interface EventFaqDto {
  id?: number;
  eventId: number;
  questionId: number;
  answerId: number;
}
