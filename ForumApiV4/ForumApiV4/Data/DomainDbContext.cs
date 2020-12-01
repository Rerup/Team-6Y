﻿using ForumApiV4.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ForumApiV4.Data
{
    public class DomainDbContext : DbContext
    {
        public DbSet<Post> Posts { get; set; }
        public DbSet<Comment> Comments { get; set; }
        public DbSet<Category> Categories { get; set; }
        public DomainDbContext ( DbContextOptions<DomainDbContext> options )
            : base(options)
        {
            Database.EnsureCreated();
        }
    }
}
