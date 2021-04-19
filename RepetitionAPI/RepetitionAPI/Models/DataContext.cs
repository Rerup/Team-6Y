using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using RepetitionAPI.Models;

namespace RepetitionAPI.Models
{
    public class DataContext : DbContext
    {
        public DataContext(DbContextOptions options) : base (options)
        {
            Database.EnsureCreated();
        }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {

            base.OnModelCreating(modelBuilder);
            modelBuilder.Entity<Gift>().HasData
                (
                new Gift
                {
                    Title = "Hund",
                    Description = "Golden Retriever med sløjfe om halsen",
                    GiftNumber = 1,
                    CreationDate = DateTime.Now,
                    BoyGift = true,
                    GirlGift = true

                }) ; 
            modelBuilder.Entity<Gift>().Property(p => p.RowVersion).IsConcurrencyToken();



        }
        public DbSet<Gift> Gifts { get; set; }
    }
}
