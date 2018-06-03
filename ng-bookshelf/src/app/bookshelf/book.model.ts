import { Author } from './author.model';

export class Book {
  isbn: string;
  title: string;
  subtitle: string;
  authors: Author[];
  published: string;
  publisher: string;
  pages: number;
  description: string;
  inStock: boolean;
}
