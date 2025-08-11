export type AuthorDTO = { id: number; name?: string; firstName?: string; lastName?: string };
export type PublisherDTO = { id: number; name: string };

export type Book = {
  id: number;
  title: string;
  isbn: string;
  publicationYear: number;
  publisher?: PublisherDTO | null;
  authors?: AuthorDTO[] | null;
  price?: number;
  currency?: string;
  coverUrl?: string;
  description?: string;
};

export function authorDisplayName(a?: AuthorDTO): string {
  if (!a) return "";
  if (a.name) return a.name;
  const parts = [a.firstName, a.lastName].filter(Boolean);
  return parts.join(" ");
}
