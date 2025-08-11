export type BorrowRecordDTO = {
  id: number;
  userId: number;
  username?: string;
  bookId: number;
  bookTitle?: string;
  borrowDate: string; // ISO
  returnDate?: string | null; // ISO
  returned: boolean;
};
